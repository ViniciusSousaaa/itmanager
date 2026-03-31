# 💻 IT Asset Manager

Sistema de gerenciamento de ativos de TI desenvolvido para controle de hardware corporativo, focado em facilitar o dia a dia de equipes de suporte e infraestrutura.

## 🚀 Tecnologias Utilizadas
* **Back-end:** Java 21 | Spring Boot 4.0.5 | Spring Data JPA
* **Banco de Dados:** PostgreSQL
* **Front-end:** Thymeleaf | Bootstrap 5 (Responsive UI)
* **Ferramentas:** Maven | Lombok | Git/GitHub

## 🛠️ Funcionalidades Principais
- **Dashboard Estatístico:** Cards com contagem em tempo real de ativos Totais, Disponíveis, Em Uso e Em Manutenção.
- **Busca Dinâmica:** Filtro de equipamentos por Nome ou Número de Série.
- **Gestão de Ativos (CRUD):** Cadastro, edição detalhada (incluindo campo de observações técnicas) e exclusão.
- **Tratamento de Exceções:** Validação de unicidade para Números de Série, evitando duplicidade no banco de dados.

## ⚙️ Como rodar o projeto
1. Clone o repositório.
2. Crie um banco PostgreSQL chamado `it_manager`.
3. Configure o `application.properties` com suas credenciais.
4. Execute a aplicação via IDE ou Maven.