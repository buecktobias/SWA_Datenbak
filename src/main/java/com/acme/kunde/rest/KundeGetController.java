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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.kunde.rest;

import com.acme.kunde.entity.Kunde;
import com.acme.kunde.service.KundeReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.acme.kunde.rest.KundeGetController.REST_PATH;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_MODIFIED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * Eine @RestController-Klasse bildet die REST-Schnittstelle, wobei die HTTP-Methoden, Pfade und MIME-Typen auf die
 * Methoden der Klasse abgebildet werden. Public, damit Pfade für Zugriffsschutz verwendet werden können.
 * <img src="../../../../../asciidoc/KundeGetController.svg" alt="Klassendiagramm">
 *
 * @author <a href="mailto:Juergen.Zimmermann@h-ka.de">Jürgen Zimmermann</a>
 */
@RestController
@RequestMapping(REST_PATH)
@Tag(name = "Kunde API")
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings({"ClassFanOutComplexity", "TrailingComment"})
public class KundeGetController {
    /**
     * Pfad, um Nachnamen abzufragen.
     */
    public static final String NACHNAME_PATH = "/nachname"; //NOSONAR

    /**
     * Basispfad für die REST-Schnittstelle.
     */
    static final String REST_PATH = "/rest"; //NOSONAR

    /**
     * Muster für eine UUID. [\dA-Fa-f]{8}-([\dA-Fa-f]{4}-){3}[\dA-Fa-f]{12} enthält eine "capturing group"
     * und ist nicht zulässig.
     */
    static final String ID_PATTERN =
        "[\\dA-Fa-f]{8}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{12}";

    private final KundeReadService service;
    private final UriHelper uriHelper;

    // https://localhost:8080/swagger-ui.html
    /**
     * Suche anhand der Kunde-ID als Pfad-Parameter.
     *
     * @param id ID des zu suchenden Kunden
     * @param version Versionsnummer aus dem Header If-None-Match
     * @param request Das Request-Objekt, um Links für HATEOAS zu erstellen.
     * @param authentication Authentication-Objekt für Security
     * @return Ein Response mit dem Statuscode 200 und dem gefundenen Kunden mit Atom-Links oder Statuscode 404.
     */
    @GetMapping(path = "{id:" + ID_PATTERN + "}", produces = HAL_JSON_VALUE)
    @Operation(summary = "Suche mit der Kunde-ID", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Kunde gefunden")
    @ApiResponse(responseCode = "404", description = "Kunde nicht gefunden")
    @SuppressWarnings("ReturnCount")
    ResponseEntity<KundeModel> findById(
        @PathVariable final UUID id,
        @RequestHeader("If-None-Match") final Optional<String> version,
        final HttpServletRequest request,
        final Authentication authentication
    ) {
        final var user = (UserDetails) authentication.getPrincipal();
        log.debug("findById: id={}, version={}, user={}", id, version, user);
        // KEIN Optional https://github.com/spring-projects/spring-security/issues/3208
        //noinspection DuplicatedCode
        if (user == null) {
            return status(FORBIDDEN).build();
        }

        // Anwendungskern
        final var kunde = service.findById(id, user);
        log.debug("findById: {}", kunde);

        final var currentVersion = "\"" + kunde.getVersion() + '"';
        if (Objects.equals(version.orElse(null), currentVersion)) {
            return status(NOT_MODIFIED).build();
        }

        final var model = kundeToModel(kunde, request);
        log.debug("findById: model={}", model);
        return ok().eTag(currentVersion).body(model);
    }

    private KundeModel kundeToModel(final Kunde kunde, final HttpServletRequest request) {
        final var model = new KundeModel(kunde);
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var idUri = baseUri + '/' + kunde.getId();

        final var selfLink = Link.of(idUri);
        final var listLink = Link.of(baseUri, LinkRelation.of("list"));
        final var addLink = Link.of(baseUri, LinkRelation.of("add"));
        final var updateLink = Link.of(idUri, LinkRelation.of("update"));
        final var removeLink = Link.of(idUri, LinkRelation.of("remove"));
        model.add(selfLink, listLink, addLink, updateLink, removeLink);
        return model;
    }

    /**
     * Suche mit diversen Suchkriterien als Query-Parameter.
     *
     * @param suchkriterien Query-Parameter als Map.
     * @param request Das Request-Objekt, um Links für HATEOAS zu erstellen.
     * @return Ein Response mit dem Statuscode 200 und den gefundenen Kunden als CollectionModel oder Statuscode 404.
     */
    @GetMapping(produces = HAL_JSON_VALUE)
    @Operation(summary = "Suche mit Suchkriterien", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "CollectionModel mid den Kunden")
    @ApiResponse(responseCode = "404", description = "Keine Kunden gefunden")
    CollectionModel<? extends KundeModel> find(
        @RequestParam final MultiValueMap<String, String> suchkriterien,
        final HttpServletRequest request
    ) {
        log.debug("find: suchkriterien={}", suchkriterien);

        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var models = service.find(suchkriterien)
            .stream()
            .map(kunde -> {
                final var model = new KundeModel(kunde);
                model.add(Link.of(baseUri + '/' + kunde.getId()));
                return model;
            })
            .toList();
        log.debug("find: {}", models);
        return CollectionModel.of(models);
    }

    /**
     * Abfrage, welche Nachnamen es zu einem Präfix gibt.
     *
     * @param prefix Nachname-Präfix als Pfadvariable.
     * @return Die passenden Nachnamen oder Statuscode 404, falls es keine gibt.
     */
    @GetMapping(path = NACHNAME_PATH + "/{prefix}", produces = APPLICATION_JSON_VALUE)
    String findNachnamenByPrefix(@PathVariable final String prefix) {
        log.debug("findNachnamenByPrefix: {}", prefix);
        final var nachnamen = service.findNachnamenByPrefix(prefix);
        log.debug("findNachnamenByPrefix: {}", nachnamen);
        return nachnamen.toString();
    }
}
