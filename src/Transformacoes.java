import java.io.*;

public class Transformacoes {
    public static void main(String[] args) {

        String arquivo_entrada = "dadosSaida/password_classifier.csv";
        String arquivo_saida_formated = "dadosSaida/passwords_formated_data.csv";
        String arquivo_saida_filtrado = "dadosSaida/passwords_classifier.csv";

        try (BufferedReader entrada = new BufferedReader(new FileReader(arquivo_entrada));
             BufferedWriter escrever_formated  = new BufferedWriter(new FileWriter(arquivo_saida_formated ));
             BufferedWriter escrever_filtrado = new BufferedWriter(new FileWriter(arquivo_saida_filtrado))) {

            String linha = entrada.readLine(); // Lê o cabeçalho
            if (linha != null) {
                escrever_formated.write(linha); // Escreve o cabeçalho no arquivo de data formatada
                escrever_formated.newLine();
                escrever_filtrado.write(linha); // Escreve o cabeçalho no arquivo de senhas filtradas
                escrever_filtrado.newLine();
            }

            // Lê linha por linha do arquivo de entrada
            while ((linha = entrada.readLine()) != null) {

                String[] coluna = linha.split(",");
                int tamanho = coluna.length;
                if (tamanho > 3) {
                    String nova_formatacao_data = transformacao(coluna[3]); // Transforma a data
                    String nova_linha = coluna[0] + "," + coluna[1] + "," + coluna[2] + "," + nova_formatacao_data  + "," + coluna[4];
                    escrever_formated.write(nova_linha);
                    escrever_formated.newLine();

                    // Filtra senhas pela categoria "Boa" e "Muito Boa"
                    if (tirar_espacos(coluna[4]).equals("Boa") || tirar_espacos(coluna[4]).equals("Muito Boa")) {
                        escrever_filtrado.write(nova_linha); // Escreve a linha no arquivo de senhas filtradas
                        escrever_filtrado.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String transformacao(String coluna) {

        //transforma para o formato
        String[] partes = coluna.split(" ");
        int tamanho = partes.length;
        if (tamanho < 2) {
            System.out.println("Data inválida");
            return "Data Inválida";
        }
        String[] partes_02 = partes [0].split("-");
        if (partes_02.length < 3) {
            System.out.println("Data inválida");
            return "Data Incompleta";
        }
        return partes_02[2] + "/" + partes_02[1] + "/" + partes_02[0];
    }

    private static String tirar_espacos(String input) {

        char[] entrada = input.toCharArray(); // Converte a string em um array
        int comeca = 0;
        int tamanho = entrada.length - 1;

        while (comeca<= tamanho && entrada[comeca] == ' ') {
            comeca++;
        }
        while (tamanho >= comeca && entrada[tamanho] == ' ') {
            tamanho--;
        }
        return new String(entrada, comeca, tamanho - comeca + 1);
    }
}