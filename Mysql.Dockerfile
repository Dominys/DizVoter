FROM mysql:8.0.31

HEALTHCHECK --interval=1s --timeout=60s --retries=20 CMD mysql --database=$MYSQL_DATABASE --password=$MYSQL_ROOT_PASSWORD --execute="SELECT count(table_name) > 0 FROM information_schema.tables;" || exit 1
