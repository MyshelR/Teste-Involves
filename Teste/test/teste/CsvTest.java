package teste;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Myshel
 */
public class CsvTest {
    
    public CsvTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of nomeArquivo method, of class Csv.
     * @throws java.lang.Exception
     */
    @Test
    public void testNomeArquivo() throws Exception {
        System.out.println("nomeArquivo");
        String local = "";
        String nome = "";
        Csv instance = new Csv();
        String expResult = "";
        String result = instance.nomeArquivo(local, nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of count method, of class Csv.
     * @throws java.lang.Exception
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        Csv instance = new Csv();
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
    }

    /**
     * Test of countDistinct method, of class Csv.
     * @throws java.lang.Exception
     */
    @Test
    public void testCountDistinct() throws Exception {
        System.out.println("countDistinct");
        String coluna = "";
        Csv instance = new Csv();
        int expResult = -1;
        int result = instance.countDistinct(coluna);
        assertEquals(expResult, result);
    }

    /**
     * Test of textoFormatado method, of class Csv.
     */
    @Test
    public void testTextoFormatado() {
        System.out.println("textoFormatado");
        String palavra = "";
        Csv instance = new Csv();
        String expResult = "";
        String result = instance.textoFormatado(palavra);
        assertEquals(expResult, result);
    }

    /**
     * Test of filter method, of class Csv.
     * @throws java.lang.Exception
     */
    @Test
    public void testFilter() throws Exception {
        System.out.println("filter");
        String nome = "";
        String propriedade = "";
        Csv instance = new Csv();
        String expResult = "";
        String result = instance.filter(nome, propriedade);
        assertEquals(expResult, result);
    }    
}
