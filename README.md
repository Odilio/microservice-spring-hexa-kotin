## Microservice boilerplate
Exemplo de microsserviço em koltin e spring boot usando a arquitetura hexagonal como base de estrutura

# Executando Localmente
Para executar localmente é necessario possuir instalado o k8s e alguma ferramenta,
podem ser utilizados o [minikube](https://minikube.sigs.k8s.io/docs/start/), ou [kind](https://kind.sigs.k8s.io/).

Crie uma imagem com o JIB usando o comando `mvn compile jib:build`.

Tendo ambiente montado, podemos efetuar o deploy ou executar em modo dev 
usando a ferramenta do Google [skaffold](https://skaffold.dev/) com o comando `skaffold dev --port-forward`

Mais informações detalhadas na [wiki](https://wiki.softfocus.com.br/books/credilab-for-developers-019/page/stack-javakotlin)

#Ambientes de homologação e produção
O arquivo application.yml deve ser removido do projeto ao finalizar a fase de desenvolvimento.

Um novo arquivo com o nome do microsserviço (ex: microservice-boilerplate.yml) deve ser criado em todas as branches 
do projeto [microservice-properties-v2](https://bitbucket.org/softfocusbr/microservice-properties-v2/)

# Informações extras

Na pasta recursos-extras existe uma coleção com todos os endpoints do ms mapeado,
todos estão com autententicação herdada do ambiente, por exemplo

Na pasta Homologação existe a configuração de autenticação, somente será necessário
obter o token uma vez para a pasta e as requests estarão a compartilhá-lo.
