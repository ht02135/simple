package simple.packages.spring.core;

//pojo
public class Book {
	   private Integer id;
	   private String name;

	   public Book() {
		   super();
	   }
	 
	   public Integer getId() {
	      return id;
	   }
	   public void setId(Integer id) {
	      this.id = id;
	   }
	   public String getName() {
	      return name;
	   }
	   public void setName(String name) {
	      this.name = name;
	   }

	   @Override
	   public String toString() {
	      return "Book [Id=" + id + ", name=" + name + "]";
	   }
}
