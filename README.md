Grupo: Luiz Inácio, Rui Fernando, Nidlan

Funcionamento do Projeto

Pré-requisito
* Certifique-se de que o arquivo passwords.csv está localizado na pasta dadosEntrada/.

Etapa 1 – Classificação de Senhas
* Execute a classe **ClassificadorPassword**.
* Ela irá ler o arquivo passwords.csv e gerar um novo arquivo chamado "password_classifier.csv" na pasta dadosSaida/, contendo todas as senhas do arquivo original acompanhadas de suas respectivas classificações (Muito Ruim, Ruim, Fraca, Boa ou Muito Boa).

Etapa 2 – Transformação dos Dados
* Em seguida, execute a classe **transformacao**.
* Essa etapa realiza duas ações:
  * Gera o arquivo "passwords_formated_data.csv" com as datas convertidas para o formato brasileiro (dd/MM/yyyy).
  * Gera o arquivo "passwords_classifier.csv" (também na pasta dadosSaida/), contendo apenas as senhas classificadas como "Boa" ou "Muito Boa", com as datas já formatadas corretamente.
