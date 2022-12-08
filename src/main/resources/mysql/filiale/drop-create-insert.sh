#!/usr/bin/env bash
mysql --database=filiale < drop.sql
mysql --database=filiale < create.sql
mysql --database=filiale < insert.sql
