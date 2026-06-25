# 🔧 Projeto Oficina Digital: Do Papel ao Código

## 📌 Por que este sistema existe? (O Propósito)

Este projeto não nasceu de um tutorial de internet ou de uma aula genérica. Ele nasceu da observação direta do meu cotidiano de trabalho. Atualmente, a empresa onde trabalho enfrenta desafios reais por depender de **blocos de papel** para gerenciar ordens de serviço e orçamentos.

Eu decidi desenvolver este sistema para **resolver um problema real**. O objetivo é transformar um processo manual, lento e falho em um fluxo digital seguro, organizado e profissional.

### 🚩 O Problema que eu decidi resolver:
* **Perda de Informação:** Papéis rasgam, somem ou ficam com a letra ilegível, causando prejuízo e confusão com clientes.
* **Falta de Memória:** Sem um banco de dados, é impossível saber rapidamente o que foi feito em um veículo há seis meses.
* **Insegurança:** Não há travas. Qualquer erro humano no papel pode gerar um orçamento errado ou a perda de um dado importante.

---

## 🚀 A Solução: Modernização com Java & Spring Boot

Desenvolvi esta API para ser o alicerce dessa mudança. O sistema foi construído pensando em quem está no "chão da oficina", mas precisa de uma gestão de "escritório" impecável.

### 🛡️ Diferenciais que implementei para uso real:
* **Fim do "Apagar Dados":** Usei a técnica de **Inativação Lógica**. Se um funcionário sai da empresa, ele é desativado, mas o histórico de tudo o que ele produziu permanece intacto para a oficina.
* **Trava de Segurança:** O sistema é inteligente o suficiente para impedir que um funcionário inativo gere novos orçamentos, evitando erros operacionais.
* **Comunicação Limpa (DTOs):** A API foi desenhada para ser conectada a qualquer interface futura (Web ou Mobile), garantindo que os dados do banco fiquem protegidos.
* **Filtros Ágeis:** Chega de revirar pastas. Agora, com um comando, filtramos serviços por data, placa ou nome da empresa.

---

## 🛠️ Tecnologias que dão vida ao sistema
Escolhi ferramentas que o mercado utiliza para sistemas de grande escala, garantindo que a oficina tenha uma tecnologia de ponta:
* **Java 17+** como linguagem base.
* **Spring Boot** para a estrutura da API.
* **Spring Data JPA** para o gerenciamento inteligente do banco de dados.
* **Lombok** para manter o código limpo e fácil de dar manutenção.

---

## 🎓 Sobre o Desenvolvedor
Meu nome é **Rodolfo**, sou estudante de Engenharia de Software (formatura em Junho/2026). Estou usando o que aprendi na faculdade e cursos para modernizar o meu ambiente de trabalho e mudar a forma como as ordens de serviço são geradas.

**Este é um projeto vivo, criado para substituir o papel e trazer a oficina para a era digital.**
