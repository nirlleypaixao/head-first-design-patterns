# Capítulo 1: Bem-Vindos ao Design Patterns

## Alguém já resolveu seus problemas

Nesse capítulo, você vai aprender porque e como você pode explorar o conhecimento e lições aprendidas por outros desenvolvedores que já estiveram com os mesmos problemas de design e sobreviveram à jornada.

## Tudo começou com um simples app de simulador de patos

Joe trabalha para uma empresa que faz um game de sucesso um simulador de lagoa de patos, o **SimDuck**. O jogo pode mostrar uma larga variedade de espécies de patos nadando e fazendo sons de *quack*. Os designers iniciais do sistema usaram técnicas de OO padrão e criaram uma superclass `Duck` na qual todos os outros tipos de Duck herdam.
![Duck](Duck.png "SimDuck-UML")

## Mas agora nós precisamos que os patos VOEM!
Os executivos decidiram que patos voadores são o que o simulador precisa para varrer a concorrência. E, claro, o gerente do Joe disse para eles que não seria um problema para o Joe criar algo em uma semana. Afinal — disse o gerente — "ele é *O Cara* na programação OO".
![Duck](Duck-Fly.png "SimDuck-UML")

## Mas algo deu terrivelmente errado...
*O que aconteceu?*  
Joe falhou em notar que nem todas as subclasses de `Duck` devem VOAR. Quando Joe adicionou novos comportamentos para a superclasse `Duck`, ele também estava adicionando comportamentos que não eram apropriados para algumas subclasses de `Duck`. Agora ele possui alguns objetos inanimados voando, como pato de Borracha, no **SimDuck**.
![Duck](Duck-Something-Went-Wrong.png "SimDuck-UML")

## Que tal uma interface?
Joe percebeu que herança provavelmente não foi a solução, porque ele pegou uma anotação que diz que os executivos desejam atualizar o SimDuck a cada 6 meses (de uma maneira que eles ainda não decidiram).
Joe sabe que as especificações continuarão mudando e ele seria forçado em olhar e possivelmente fazer um override de voa() e quack() para cada nova subclasse de Duck que serão adicionadas para o programa... Eternamente.

Então, ele precisa de uma maneira mais elegante para ter somente algumas (mas não todas) dos tipos duck voe ou faça quack.
![Duck](Duck-Inrterfaces.png "SimDuck-UML")


## O que você faria se fosse o Joe?
Nós sabemos que nem todas as subclasses devem ter comportamentos voa ou quack, então herança não é a resposta certa. Mas enquanto temos subclasses implementando Voa ou Quack resolve parte do problema (sem patos de borrachas voadores), isso destrói complemente o reuso do código para esses comportamentos, então isso cria um novo pesadelo de manutenção. E claro pode ter mais que um tipo de comportamento voa() mesmo entre patos que fly()

A este ponto você deve estar esperando por um Design Pattern cavalgando em um azalão branco para salvar o dia. Mas que diversão isso teria? Não, nós vamos descobrir uma solução a moda antiga aplicando o bom e velho OO, princípios de software design.

## Zerando o problema
Então nós sabemos que usar herança não funcionou muito bem, desde que o comportamento de Pato
continua mudando através das subclasses, e não é apropriado para todas as subclasses ter tais comportamentos.
As interfaces Flyable e Quackable soavam promissoras a primeira vista - somente patos que realmente voavam terão Flyable, e etc. - Exceto 
Java que tipicamente não tem código de implementação, então nenhum reuso de código. Em nenhum dos casos, a cada momento que você precisa modificar o comportamento, você será frequentemente forçado
a rastrear e mudar em todas as subclasses diferentes onde o comportamento é definido, provavelmente introduzindo alguns bugs pelo caminho.

Por sorte, tem um princípio de design para esse tipo de situação :)

Design Principle:
Identifique os aspectos da sua aplicação que variam e separe elas do que não muda.

Em outras palavras, se você tem algum aspecto do seu código que está mudando , digamos, a cada nova requisição, então você sabe que tem um comportamento que precissa ser sacado e separado de todas as coisas que não mudam.

Aqui está outra maneira de pensar sobre esse princípio: *pegue as partes que variam e encapsule elas, para que você possa alterar ou estender as partes que variam sem afetar aquelas que não variam.*

Tão simples como esse conceito é, isso forma a base para quase todos os design patterns. Todos os patterns fornecem uma maneira para deixar *alguma parte de um sistema variar independentemente das demais partes*.

Ok, hora de tirar comportamento de Pato fora da classe Pato!

## Separando o que muda daquilo que se mantem o mesmo

## Designing o comportamento de Pato

## "Programe para uma interface" realmente significa "Programe para um supertipo."

## Implementando os comportamentos de Pato

## Integrando os comportamentos de Pato

## Mais Integração

## Testando o código do Pato

## Escreva e compile o VoaComportamento interface e as duas classes de implementação de comportamentos (VoaComAsas e NaoVoa).

## Testando o código do Pato, continuação...

## Setando o comportamento dinamicamente

# A grande sacada em encapsular comportamentos

## HAS-A pode ser melhor do que IS-A

## Parabéns pelo seu primeiro pattern!

Você acabou de aplicar seu primeiro pattern, o **STRATEGY PATTERN**. Você usou o Strategy para refatorar o app SimDuck.
Graças à esse pattern, o simulador está pronto para quaisquer mudanças.
Agora que percorremos um longo caminho para aprendê-lom aqui vai uma definição formal desse pattern.

**The Strategy Pattern** define uma família de algoritmos, encapsula cada um, e faz eles serem intercambiáveis. 
O Strategy permite o algoritmo variarem independentemente dos clientes que a utilizam.
