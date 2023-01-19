
class ReportingStructure {

    private Employee employee;
    private int numOfReports;

    public ReportingStructure(Employee employee, int numOfReports){
        this.employee = employee;
        this.numOfReports = numOfReports;
    }

    public Employee getEmployee(){
        return employee;
    }

    public int getNumOfReports(){
        return numOfReports;
    }
}