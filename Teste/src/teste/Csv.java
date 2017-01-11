package teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Csv { 

    String arquivo = "";

    public String nomeArquivo(String local, String nome) throws IOException {
        if (local.equals("\\")) {
            local = "";
        }
        String caminho = local + nome;
        arquivo = caminho.replace("\\", "\\\\");
        return caminho;
    }

    public int count() throws IOException {
        String line;

        List<List<String>> csvData = new ArrayList<>();
        File arquivo1 = new File(arquivo);
        if (arquivo1.exists()) {
            BufferedReader stream = new BufferedReader(new FileReader(arquivo));
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(",");
                List<String> dataLine = new ArrayList<>(splitted.length);
                dataLine.addAll(Arrays.asList(splitted));
                csvData.add(dataLine);
            }

            if (csvData.isEmpty()) {
                return 0;
            } else {
                return csvData.size() - 1;
            }
        } else {
            return 0;
        }
    }

    public int countDistinct(String coluna) throws FileNotFoundException, IOException {
        String line;
        BufferedReader stream = null;
        List<List<String>> csvData = new ArrayList<>();
        int pos = 0;
        boolean existe = false;

        File arquivo1 = new File(arquivo);
        if (arquivo1.exists()) {
            stream = new BufferedReader(new FileReader(arquivo));
            if (stream == null) {
                stream.close();
            }
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(",");
                List<String> dataLine = new ArrayList<>(splitted.length);
                dataLine.addAll(Arrays.asList(splitted));
                csvData.add(dataLine);
            }

            if (csvData.isEmpty()) {
                return 0;
            } else {
                for (int i = 0; i < csvData.get(0).size(); i++) {
                    if (csvData.get(0).get(i).equalsIgnoreCase(coluna)) {
                        pos = i;
                        existe = true;
                    }
                }

                if (existe) {
                    List<String> resultado = new ArrayList<>();
                    for (int i = 0; i < csvData.size(); i++) {
                        resultado.add(csvData.get(i).get(pos));
                    }
                    resultado = new ArrayList(new HashSet(resultado));
                    return resultado.size() - 1;

                } else {
                    return 0;
                }
            }
        } else {
            return -1;
        }
    }
    
    public String textoFormatado(String palavra){
        if(palavra.length()>3){
            palavra = palavra.substring(1, palavra.length()-1);
        }        
        return palavra;
    }

    public String filter(String nome, String propriedade) throws FileNotFoundException, IOException {
        String line;
        BufferedReader stream;
        StringBuilder sb = new StringBuilder();
        List<List<String>> csvData = new ArrayList<>();
        boolean coluna = false;
        boolean colPropriedade = false;
        List<List<String>> resultados = new ArrayList<>();
        String saida = "";

        File arquivo1 = new File(arquivo);
        if (arquivo1.exists()) {
            stream = new BufferedReader(new FileReader(arquivo));
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(",");
                List<String> dataLine = new ArrayList<>(splitted.length);
                dataLine.addAll(Arrays.asList(splitted));
                csvData.add(dataLine);
            }
            
            int pos = 0;
            List<String> cabecalho = csvData.get(0);
            for (int i = 0; i < cabecalho.size(); i++) {
                if (cabecalho.get(i).equalsIgnoreCase(nome)) {
                    pos = i;
                    coluna = true;
                }
            }
            
            if (coluna) {
                for (int i = 0; i < csvData.size(); i++) {
                    if (csvData.get(i).get(pos).equalsIgnoreCase(propriedade)) {                        
                        resultados.add(csvData.get(i));                        
                        colPropriedade = true;
                    }
                }

            } else {                
                System.out.println("Coluna não encontrada!");
            }
            if (!colPropriedade) {
                System.out.println("Propriedade não encontrada!");
            } else {
                for (int i = 0; i < resultados.size(); i++) {                                                 
                    saida = saida + textoFormatado(resultados.get(i).toString()) + "\n";                    
                }
            }
            if(saida.contains("[, , , , , , , , , ]")){
                saida = saida.replaceAll("[, , , , , , , , , ]", "");
            }

            //saida = textoFormatado(csvData.get(0).toString()) + "\n" + saida;
            sb.append(textoFormatado(csvData.get(0).toString())).append("\n").append(saida);
            
            return sb.toString();
        } else {
            System.out.println("***********************\n"
                    + "Resultado: 0 Valore(s) - Arquivo vazio."
                    + "\n***********************");
        }
        return "";
    }

    public void menu() throws IOException {
        Scanner s = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n;

        do {
            System.out.println("MENU \n 1 - Count \n 2 - Count Distinct \n 3 - Filter "
                    + "\n 4 - Inserir novo arquivo \n 0 - Sair");
            try {
                n = Integer.parseInt(s.nextLine());
                switch (n) {
                    case 1:
                        if (count() > 1) {
                            System.out.println("***********************\n"
                                    + "Resultado: " + (count()) + " Valore(s)"
                                    + "\n***********************");
                        } else {
                            System.out.println("***********************\n"
                                    + "Resultado: 0 Valore(s) - Arquivo vazio."
                                    + "\n***********************");
                        }
                        break;
                    case 2:
                        System.out.println("Qual a coluna quer pesquisar?");
                        String coluna = in.readLine();
                        if (countDistinct(coluna) == 0) {
                            System.out.println("***********************\n"
                                    + "Resultado: " + (countDistinct(coluna)) + " Valore(s) - Coluna não encontrada."
                                    + "\n***********************");
                        } else if(countDistinct(coluna) == -1){
                            System.out.println("***********************\n"
                                    + "Resultado: 0 Valore(s) - Arquivo vazio."
                                    + "\n***********************");
                        }else if(countDistinct(coluna) > 0){
                            System.out.println("***********************\n"
                                    + "Resultado: " + (countDistinct(coluna)) + " Valore(s)."
                                    + "\n***********************");
                        }
                        break;
                    case 3:
                        System.out.println("Qual a coluna quer pesquisar?");
                        String nome = in.readLine();
                        System.out.println("Qual a propriedade quer pesquisar?");
                        String propriedade = in.readLine();
                        System.out.println(filter(nome, propriedade));
                        break;
                    case 4:
                        System.out.println("Digite o local do arquivo: " + "\nExemplo: C:\\Desktop");
                        String local = in.readLine() + "\\";
                        if (local.equals("\\")) {
                            System.out.println("*********\nLocal vazio.\n*********");
                        }
                        System.out.println("Digite o nome do arquivo com a extensão: " + "\nExemplo: cidades.csv");
                        String arq = in.readLine();
                        if (arq.isEmpty()) {
                            System.out.println("*********\nNome vazio.\n*********");
                        }
                        nomeArquivo(local, arq);
                        break;
                    case 0:
                        System.out.println("Sistema Encerrado.");
                        System.exit(0);
                    default:
                        System.out.println("Opção incorreta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!");
            }
        } while (true);
    }
}
