interface Element {
    void accept(Visitor visitor);
}

interface Visitor {
    void visitCompany(Company company);
    void visitDepartment(Department department);
    void visitEmployee(Employee employee);
}

class Employee implements Element {
    private String position;
    private double salary;

    public Employee(String position, double salary) {
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitEmployee(this);
    }
}

class Department implements Element {
    private String name;
    private Employee[] employees;

    public Department(String name, Employee[] employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitDepartment(this);
        for (Employee employee : employees) {
            employee.accept(visitor);
        }
    }
}

class Company implements Element {
    private String name;
    private Department[] departments;

    public Company(String name, Department[] departments) {
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public Department[] getDepartments() {
        return departments;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCompany(this);
        for (Department department : departments) {
            department.accept(visitor);
        }
    }
}

class SalaryReportVisitor implements Visitor {
    @Override
    public void visitCompany(Company company) {
        System.out.println("Generating total company salary report");
    }

    @Override
    public void visitDepartment(Department department) {
        System.out.println("Generating salary report for specific department");
    }

    @Override
    public void visitEmployee(Employee employee) {
        System.out.println("Including employee data in report");
    }
}

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("Manager", 5000);
        Employee e2 = new Employee("Developer", 4000);
        Employee e3 = new Employee("Tester", 3000);
        Department dep1 = new Department("IT", new Employee[]{e1, e2, e3});

        Employee e4 = new Employee("HR Manager", 4500);
        Employee e5 = new Employee("Recruiter", 3200);
        Department dep2 = new Department("HR", new Employee[]{e4, e5});

        Company company = new Company("TechCorp", new Department[]{dep1, dep2});

        SalaryReportVisitor reportVisitor = new SalaryReportVisitor();

        company.accept(reportVisitor);

        dep1.accept(reportVisitor);
    }
}
