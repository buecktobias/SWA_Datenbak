/*
 * Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.acme.kunde.service;

import com.acme.kunde.entity.Adresse;
import com.acme.kunde.entity.Kunde;
import com.acme.kunde.entity.Umsatz;
import com.acme.kunde.repository.KundeRepository;
import com.acme.kunde.repository.SpecBuilder;
import com.acme.kunde.security.CustomUser;
import com.acme.kunde.security.Rolle;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static com.acme.kunde.entity.FamilienstandType.LEDIG;
import static com.acme.kunde.entity.GeschlechtType.WEIBLICH;
import static com.acme.kunde.entity.InteresseType.LESEN;
import static com.acme.kunde.entity.InteresseType.REISEN;
import static java.math.BigDecimal.ONE;
import static java.time.LocalDateTime.now;
import static java.util.Locale.GERMANY;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.junit.jupiter.api.condition.JRE.JAVA_19;
import static org.junit.jupiter.api.condition.JRE.JAVA_20;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("unit")
@Tag("service_read")
@DisplayName("Anwendungskern fuer Lesen")
@ExtendWith({MockitoExtension.class, SoftAssertionsExtension.class})
@EnabledForJreRange(min = JAVA_19, max = JAVA_20)
@SuppressWarnings({"ClassFanOutComplexity", "InnerTypeLast", "WriteTag"})
class KundeReadServiceTest {
    private static final String ID_VORHANDEN = "00000000-0000-0000-0000-000000000001";
    private static final String ID_NICHT_VORHANDEN = "99999999-9999-9999-9999-999999999999";
    private static final String PLZ = "12345";
    private static final String ORT = "Testort";
    private static final String NACHNAME = "Nachname-Test";
    private static final String EMAIL = "theo@test.de";
    private static final LocalDate GEBURTSDATUM = LocalDate.of(2018, 1, 1);
    private static final Currency WAEHRUNG = Currency.getInstance(GERMANY);
    private static final String HOMEPAGE = "https://test.de";
    private static final String USERNAME = "test";
    private static final String USERNAME_ADMIN = "admin";
    private static final String PASSWORD = "p";

    private KundeRepository repo;
    private KundeReadService service;

    @InjectSoftAssertions
    private SoftAssertions softly;

    // https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#resetting_mocks
    @BeforeEach
    void beforeEach() {
        repo = mock(KundeRepository.class);
        final SpecBuilder specBuilder = new SpecBuilder();
        service = new KundeReadService(repo, specBuilder);
    }

    // https://raw.githubusercontent.com/omnidan/node-emoji/master/lib/emoji.json
    @Test
    @DisplayName("Immer erfolgreich")
    void immerErfolgreich() {
        assertThat(true).isTrue();
    }

    @Test
    @Disabled
    @DisplayName("Noch nicht fertig")
    void nochNichtFertig() {
        //noinspection DataFlowIssue
        assertThat(false).isTrue();
    }

    @ParameterizedTest(name = "Suche alle Kunden")
    @ValueSource(strings = NACHNAME)
    @DisplayName("Suche alle Kunden")
    void findAll(final String nachname) {
        // given
        final var kunde = createKundeMock(nachname);
        final var kundenMock = List.of(kunde);
        when(repo.findAll()).thenReturn(kundenMock);
        final Map<String, List<String>> keineSuchkriterien = new LinkedMultiValueMap<>();

        // when
        final var kunden = service.find(keineSuchkriterien);

        // then
        assertThat(kunden).isNotEmpty();
    }

    @ParameterizedTest(name = "[{index}] Suche mit vorhandenem Nachnamen: nachname={0}")
    @ValueSource(strings = NACHNAME)
    @DisplayName("Suche mit vorhandenem Nachnamen")
    void findByNachname(final String nachname) {
        // given
        final var kunde = createKundeMock(nachname);
        final var kundenMock = List.of(kunde);
        when(repo.findByNachname(nachname)).thenReturn(kundenMock);
        final MultiValueMap<String, String> suchkriterien = new LinkedMultiValueMap<>();
        suchkriterien.add("nachname", nachname);

        // when
        final var kunden = service.find(suchkriterien);

        // then
        assertThat(kunden)
            .isNotNull()
            .isNotEmpty();
        kunden
            .stream()
            .map(Kunde::getNachname)
            .forEach(nachnameKunde -> softly.assertThat(nachnameKunde).containsIgnoringCase(nachname));
    }

    @ParameterizedTest(name = "[{index}] Suche mit vorhandener Emailadresse: email={1}")
    @CsvSource(NACHNAME + ',' + EMAIL)
    @DisplayName("Suche mit vorhandener Emailadresse")
    void findByEmail(final String nachname, final String email) {
        // given
        final var kunde = createKundeMock(nachname, email);
        when(repo.findByEmail(email)).thenReturn(Optional.of(kunde));
        final MultiValueMap<String, String> suchkriterien = new LinkedMultiValueMap<>();
        suchkriterien.add("email", email);

        // when
        final var kunden = service.find(suchkriterien);

        // then
        assertThat(kunden)
            .isNotNull()
            .isNotEmpty();
        kunden
            .stream()
            .map(Kunde::getEmail)
            .forEach(emailKunde -> softly.assertThat(emailKunde).containsIgnoringCase(email));
    }

    @ParameterizedTest(name = "[{index}] Suche mit nicht-vorhandener Emailadresse: email={0}")
    @ValueSource(strings = EMAIL)
    @DisplayName("Suche mit nicht-vorhandener Emailadresse")
    void findByEmailNichtVorhanden(final String email) {
        // given
        when(repo.findByEmail(email)).thenReturn(Optional.empty());
        final MultiValueMap<String, String> suchkriterien = new LinkedMultiValueMap<>();
        suchkriterien.add("email", email);

        // when
        final var notFoundException = catchThrowableOfType(
            () -> service.find(suchkriterien),
            NotFoundException.class
        );

        // then
        assertThat(notFoundException).isNotNull();
        assertThat(notFoundException.getSuchkriterien()).isEqualTo(suchkriterien);
    }

    @Nested
    @DisplayName("ANwendungskern fuer die Suche anhand der ID")
    class FindById {
        @ParameterizedTest(name = "[{index}] Suche mit vorhandener ID: id={0}")
        @CsvSource(ID_VORHANDEN + ',' + NACHNAME + ',' + USERNAME)
        @DisplayName("Suche mit vorhandener ID")
        void findById(final String idStr, final String nachname, final String username) {
            // given
            final var id = UUID.fromString(idStr);
            final var kundeMock = createKundeMock(id, nachname, username);
            final UserDetails user = new CustomUser(
                username,
                PASSWORD,
                List.of(new SimpleGrantedAuthority(Rolle.ADMIN_STR))
            );
            when(repo.findById(id)).thenReturn(Optional.of(kundeMock));

            // when
            final var kunde = service.findById(id, user);

            // then
            assertThat(kunde.getId()).isEqualTo(kundeMock.getId());
        }

        @ParameterizedTest(name = "[{index}] Suche mit nicht vorhandener ID: id={0}")
        @ValueSource(strings = ID_NICHT_VORHANDEN)
        @DisplayName("Suche mit nicht vorhandener ID")
        void findByIdNichtVorhanden(final String idStr) {
            // given
            final UserDetails admin = new CustomUser(
                USERNAME_ADMIN,
                PASSWORD,
                List.of(new SimpleGrantedAuthority(Rolle.ADMIN_STR))
            );
            final var id = UUID.fromString(idStr);
            when(repo.findById(id)).thenReturn(Optional.empty());

            // when
            final var notFoundException = catchThrowableOfType(
                () -> service.findById(id, admin),
                NotFoundException.class
            );

            // then
            assertThat(notFoundException).isNotNull();
            assertThat(notFoundException.getId()).isEqualTo(id);
        }
    }

    @Nested
    @DisplayName("Anwendungskern fuer die Suche anhand der PLZ")
    class FindByPlz {
        @ParameterizedTest(name = "[{index}] Suche mit vorhandener PLZ: plz={3}, id={0}, nachname={1}, email={2}")
        @CsvSource(ID_VORHANDEN + ',' + NACHNAME + ',' + EMAIL + ',' + PLZ)
        @DisplayName("Suche mit vorhandener PLZ")
        void findByPLZ(
            final String idStr,
            final String nachname,
            final String email,
            final String plz
        ) {
            // given
            final var id = UUID.fromString(idStr);
            final var kunde = createKundeMock(id, nachname, email, plz, USERNAME_ADMIN);
            final var kundenMock = List.of(kunde);
            final MultiValueMap<String, String> suchkriterien = new LinkedMultiValueMap<>();
            suchkriterien.add("plz", plz);
            when(repo.findAll(ArgumentMatchers.<Specification<Kunde>>any())).thenReturn(kundenMock);

            // when
            final var kunden = service.find(suchkriterien);

            // then
            assertThat(kunden)
                .isNotNull()
                .isNotEmpty();
            kunden
                .stream()
                .map(Kunde::getAdresse)
                .map(Adresse::getPlz)
                .forEach(plzKunde -> softly.assertThat(plzKunde).isEqualTo(plz));
        }

        @ParameterizedTest(name = "[{index}] Suche mit vorhandenem Nachnamen und PLZ: nachname={1}, plz={3}")
        @CsvSource(ID_VORHANDEN + ',' + NACHNAME + ',' + EMAIL + ',' + PLZ)
        @DisplayName("Suche mit vorhandenem Nachnamen und PLZ")
        void findByNachnamePLZ(
            final String idStr,
            final String nachname,
            final String email,
            final String plz
        ) {
            // given
            final var id = UUID.fromString(idStr);
            final var kundeMock = createKundeMock(id, nachname, email, plz, USERNAME_ADMIN);
            final var kundenMock = List.of(kundeMock);
            final MultiValueMap<String, String> suchkriterien = new LinkedMultiValueMap<>();
            suchkriterien.add("nachname", nachname);
            suchkriterien.add("plz", plz);
            when(repo.findAll(ArgumentMatchers.<Specification<Kunde>>any())).thenReturn(kundenMock);

            // when
            final var kunden = service.find(suchkriterien);

            // then
            assertThat(kunden)
                .isNotNull()
                .isNotEmpty();
            kunden.forEach(kunde -> {
                softly.assertThat(kunde.getNachname()).isEqualToIgnoringCase(nachname);
                softly.assertThat(kunde.getAdresse().getPlz()).isEqualTo(plz);
            });
        }
    }

    // -------------------------------------------------------------------------
    // Hilfsmethoden fuer Mock-Objekte
    // -------------------------------------------------------------------------
    private Kunde createKundeMock(final String nachname) {
        return createKundeMock(randomUUID(), nachname, USERNAME_ADMIN);
    }

    private Kunde createKundeMock(final String nachname, final String username) {
        return createKundeMock(randomUUID(), nachname, username);
    }

    private Kunde createKundeMock(final UUID id, final String nachname, final String username) {
        return createKundeMock(id, nachname, EMAIL, PLZ, username);
    }

    @SuppressWarnings("SameParameterValue")
    private Kunde createKundeMock(
        final UUID id,
        final String nachname,
        final String email,
        final String plz,
        final String username
    ) {
        final URL homepage;
        try {
            homepage = new URL(HOMEPAGE);
        } catch (final java.net.MalformedURLException e) {
            throw new IllegalStateException(e);
        }
        return new Kunde(
            id,
            0,
            nachname,
            email,
            1,
            true,
            GEBURTSDATUM,
            homepage,
            WEIBLICH,
            LEDIG,
            List.of(LESEN, REISEN),
            new Umsatz(randomUUID(), ONE, WAEHRUNG),
            new Adresse(randomUUID(), plz, ORT),
            username,
            now(ZoneId.of("Europe/Berlin")),
            now(ZoneId.of("Europe/Berlin"))
        );
    }
}
