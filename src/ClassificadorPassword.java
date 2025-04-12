import java.io.*;

public class ClassificadorPassword {
    public static void main(String[] args) {

        String arquivoEntrada = "dadosEntrada/passwords.csv";
        String arquivoSaida = "dadosSaida/password_classifier.csv";

        // Tentar abrir os arquivos para leitura e escrita
        try (BufferedReader entrada = new BufferedReader(new FileReader(arquivoEntrada)); BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivoSaida))) {

            //Lê a primeira linha do arquvio e a ignora
            String linha = entrada.readLine();
            if (linha != null) {
                // escreve o cabeçalho no arqivo de saida, adicionando a coluna 'class'
                escrever.write(linha + ",class");
                escrever.newLine(); // quebra a linha
            }

            // le cada linha do arquivo de entrada ate o final
            while ((linha = entrada.readLine()) != null) {
                String[] fields = linha.split(",");

                if (fields.length > 1) {
                    String password = fields[1]; // pega a senha
                    String classidicacao = classifica(password); // Chama a função de classificar senha

                    escrever.write(linha + "," + classidicacao);
                    escrever.newLine();
                }
            }

            System.out.println("Classificação de senhas concluída com sucesso. O arquivo foi gerado em: " + arquivoSaida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para classificae senha
    public static String classifica(String senha) {
        int tamanho = senha.length();

        boolean minuscula = false;
        boolean maiuscula = false;
        boolean digito = false;
        boolean caracterEspecial = false;

        for (char i : senha.toCharArray()) {

            if (Character.isLowerCase(i)) {
                minuscula = true;
            } else if (Character.isUpperCase(i)) {
                maiuscula = true;
            } else if (Character.isDigit(i)) {
                digito = true;
            } else {
                caracterEspecial = true;
            }
        }

        int totaltiposDeChar = (minuscula ? 1 : 0) + (maiuscula ? 1 : 0) + (digito ? 1 : 0) + (caracterEspecial ? 1 : 0);

        if (tamanho < 5 && totaltiposDeChar == 1) {
            return "Muito ruim";
        }
        if (tamanho <= 5 && totaltiposDeChar == 1) {
            return "Ruim";
        }
        if (tamanho <= 6 && totaltiposDeChar == 2) {
            return "Fraca";
        }
        if (tamanho <= 7 && totaltiposDeChar >= 3) {
            return "Boa";
        }
        if (tamanho > 8 && totaltiposDeChar == 4) {
            return "Muito Boa";
        }

        return "Sem Classificacao";
    }
}