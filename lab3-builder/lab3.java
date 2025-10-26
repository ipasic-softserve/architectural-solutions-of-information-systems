public interface IQueryBuilder {
    IQueryBuilder select(String table, String... columns);
    IQueryBuilder where(String condition);
    IQueryBuilder limit(int limit);
    String getSQL();
}

public class PostgresQueryBuilder implements IQueryBuilder {
    private StringBuilder query = new StringBuilder();

    @Override
    public IQueryBuilder select(String table, String... columns) {
        // Select logic
        return this;
    }

    @Override
    public IQueryBuilder where(String condition) {
        // Where logic
        return this;
    }

    @Override
    public IQueryBuilder limit(int limit) {
        // Limit logic
        return this;
    }

    @Override
    public String getSQL() {
        return query.toString();
    }
}

public class MySQLQueryBuilder implements IQueryBuilder {
    private StringBuilder query = new StringBuilder();

    @Override
    public IQueryBuilder select(String table, String... columns) {
        // Select logic
        return this;
    }

    @Override
    public IQueryBuilder where(String condition) {
        // Where logic
        return this;
    }

    @Override
    public IQueryBuilder limit(int limit) {
        // Limit logic
        return this;
    }

    @Override
    public String getSQL() {
        return query.toString();
    }
}

public class QueryDirector {
    private IQueryBuilder builder;

    public QueryDirector(IQueryBuilder builder) {
        this.builder = builder;
    }

    public String buildSimpleUserQuery() {
        return builder
                .select("users", "id", "name", "email")
                .where("active = true")
                .limit(10)
                .getSQL();
    }
}

public class Main {
    public static void main(String[] args) {
        IQueryBuilder postgresBuilder = new PostgresQueryBuilder();
        QueryDirector postgresDirector = new QueryDirector(postgresBuilder);
        String postgresQuery = postgresDirector.buildSimpleUserQuery();
        System.out.println("PostgreSQL Query: " + postgresQuery);

        IQueryBuilder mysqlBuilder = new MySQLQueryBuilder();
        QueryDirector mysqlDirector = new QueryDirector(mysqlBuilder);
        String mysqlQuery = mysqlDirector.buildSimpleUserQuery();
        System.out.println("MySQL Query: " + mysqlQuery);
    }
}
