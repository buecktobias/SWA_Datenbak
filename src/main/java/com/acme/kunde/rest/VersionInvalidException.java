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

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception, falls die Versionsnummer im ETag fehlt oder syntaktisch ungültig ist.
 *
 * @author <a href="mailto:Juergen.Zimmermann@h-ka.de">Jürgen Zimmermann</a>
 */
@Getter
class VersionInvalidException extends RuntimeException {
    private final String version;

    private final HttpStatus status;

    VersionInvalidException(final HttpStatus status) {
        super("Versionsnummer fehlt");
        version = null;
        this.status = status;
    }

    VersionInvalidException(final String version, final HttpStatus status) {
        super("Ungueltige Versionsnummer " + version);
        this.version = version;
        this.status = status;
    }


    VersionInvalidException(final String version, final HttpStatus status, final NumberFormatException cause) {
        super("Ungueltige Versionsnummer " + version, cause);
        this.version = version;
        this.status = status;
    }
}
