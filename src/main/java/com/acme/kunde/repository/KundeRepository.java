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
package com.acme.kunde.repository;

import com.acme.kunde.entity.Kunde;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository für den DB-Zugriff bei Kunden.
 *
 * @author <a href="mailto:Juergen.Zimmermann@h-ka.de">Jürgen Zimmermann</a>
 */
@Repository
public interface KundeRepository extends JpaRepository<Kunde, UUID>, JpaSpecificationExecutor<Kunde> {
    @EntityGraph(attributePaths = {"adresse", "interessen"})
    @Override
    List<Kunde> findAll();

    @EntityGraph(attributePaths = {"adresse", "interessen"})
    @Override
    Optional<Kunde> findById(UUID id);

    /**
     * Kunde zu gegebener Emailadresse aus der DB ermitteln.
     *
     * @param email Emailadresse für die Suche
     * @return Optional mit dem gefundenen Kunde oder leeres Optional
     */
    @Query("""
        SELECT k
        FROM   Kunde k
        WHERE  lower(k.email) LIKE concat(lower(:email), '%')
        """)
    @EntityGraph(attributePaths = {"adresse", "interessen"})
    Optional<Kunde> findByEmail(String email);

    /**
     * Abfrage, ob es einen Kunden mit gegebener Emailadresse gibt.
     *
     * @param email Emailadresse für die Suche
     * @return true, falls es einen solchen Kunden gibt, sonst false
     */
    @SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
    boolean existsByEmail(String email);

    /**
     * Kunden anhand des Nachnamens suchen.
     *
     * @param nachname Der (Teil-) Nachname der gesuchten Kunden
     * @return Die gefundenen Kunden oder eine leere Collection
     */
    @Query("""
        SELECT   k
        FROM     Kunde k
        WHERE    lower(k.nachname) LIKE concat('%', lower(:nachname), '%')
        ORDER BY k.id
        """)
    @EntityGraph(attributePaths = {"adresse", "interessen"})
    Collection<Kunde> findByNachname(CharSequence nachname);
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
    // Collection<Kunde> findByNachnameContainingIgnoreCaseOrderByIdAsc(CharSequence nachname);

    /**
     * Abfrage, welche Nachnamen es zu einem Präfix gibt.
     *
     * @param prefix Nachname-Präfix.
     * @return Die passenden Nachnamen oder eine leere Collection.
     */
    @Query("""
        SELECT DISTINCT k.nachname
        FROM     Kunde k
        WHERE    lower(k.nachname) LIKE concat(lower(:prefix), '%')
        ORDER BY k.nachname
        """)
    Collection<String> findNachnamenByPrefix(String prefix);
}
