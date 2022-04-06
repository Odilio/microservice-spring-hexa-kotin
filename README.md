## Microservice boilerplate
Exemplo de microsserviço em koltin e spring boot usando a arquitetura hexagonal como base de estrutura

# Executando Localmente
Para executar localmente é necessario possuir instalado o k8s e alguma ferramenta,
podem ser utilizados o [minikube](https://minikube.sigs.k8s.io/docs/start/), ou [kind](https://kind.sigs.k8s.io/).

Crie uma imagem com o JIB usando o comando `mvn compile jib:build`.

Tendo ambiente montado, podemos efetuar o deploy ou executar em modo dev 
usando a ferramenta do Google [skaffold](https://skaffold.dev/) com o comando `skaffold dev --port-forward`
