import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class TestManAndRepo {

    Product product1 = new Book(1, "Книга", 99, "Книгописец");
    Product product2 = new Book(2, "Книга2", 100, "Книгописец2");
    Product product3 = new Smartphone(3, "Телефон", 9999, "Телефонодел");


    @Test
    public void shouldAddProduct() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.removeById(2);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchProduct() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        manager.searchBy("Телефон");

        Product[] expected = {product3};
        Product[] actual = manager.searchBy("Телефон");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTwoProduct() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        manager.searchBy("Книга");

        Product[] expected = {product1, product2};
        Product[] actual = manager.searchBy("Книга");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchProduct() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        manager.searchBy("Золото");

        Product[] expected = {};
        Product[] actual = manager.searchBy("Золото");

        Assertions.assertArrayEquals(expected, actual);
    }


}
