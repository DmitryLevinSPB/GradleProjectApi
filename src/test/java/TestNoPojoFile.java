import Specifications.Specification;

public class TestNoPojoFile  {
private String url = "https://reqres.in/";
    public  void firstTestNoPojo() {
       Specification.installSpecification(Specification.requestSpec(url), Specification.responseSpecOk200());

    }


}
