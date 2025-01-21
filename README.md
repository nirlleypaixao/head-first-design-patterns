# Capítulo 1: Bem-Vindos ao Design Patterns

# Alguém já resolveu seus problemas

Neste capítulo, você aprenderá por que e como pode aproveitar o conhecimento e as lições aprendidas por outros desenvolvedores que enfrentaram os mesmos problemas de design e superaram esses desafios.

---

## Tudo começou com um simples simulador de patos

Joe trabalha para uma empresa que desenvolve um jogo de simulação de lago de patos de grande sucesso, chamado **SimDuck**.  
O jogo apresenta uma grande variedade de espécies de patos, que nadam e emitem sons de "quack".  
Os primeiros designers do sistema utilizaram técnicas clássicas de programação orientada a objetos (OO) e criaram uma superclasse `Duck`, da qual todas as outras classes de pato herdam.

![Pseudo diagrama UML ](Duck.png)

---

## Mas agora precisamos que os patos VOEM!

Os executivos decidiram que patos voadores são o que o simulador precisa para superar a concorrência.  
Claro, o gerente de Joe disse a todos que isso não seria um problema para ele.  
*"Afinal", disse o gerente, "Joe é **O Cara** da programação orientada a objetos!"*

![Pseudo diagrama UML ](Duck-Fly.png)

---

## Mas algo deu terrivelmente errado...
### *O que aconteceu?*

Joe não percebeu que nem todas as subclasses de `Duck` deveriam ser capazes de voar.  
Quando ele adicionou novos comportamentos na superclasse `Duck`, acabou incluindo funcionalidades que não eram adequadas para algumas subclasses.  
Como resultado, agora o jogo tem objetos como **patos de borracha voando** no **SimDuck**!

![Pseudo diagrama UML ](Duck-Something-Went-Wrong.png)

---

## Que tal usar uma interface?

Joe percebeu que a herança provavelmente não era a solução ideal, especialmente porque ele soube que os executivos querem que o SimDuck seja atualizado a cada 6 meses com novos recursos.  
Joe sabe que as especificações continuarão mudando, e ele teria que sobrescrever constantemente os métodos `fly()` e `quack()` em cada nova subclasse de `Duck` que for adicionada ao programa.  
Essa abordagem o prenderia em um ciclo infinito de manutenção.

Ele precisa de uma maneira mais elegante de permitir que apenas algumas, mas não todas, as subclasses de pato sejam capazes de voar ou emitir som.

![Pseudo diagrama UML ](Duck-Inrterfaces.png)

---

## O que você faria se fosse o Joe?

Sabemos que nem todas as subclasses devem ter os comportamentos `fly` ou `quack`, então **a herança não é a resposta certa**.  
Implementar diretamente esses comportamentos nas subclasses resolveria parte do problema, mas destruiria completamente a reutilização de código e criaria um pesadelo de manutenção.

Além disso, pode haver mais de um tipo de comportamento para `fly()`, mesmo entre patos que realmente voam.

## Zerando o problema
Sabemos que usar herança não funcionou muito bem, já que o comportamento dos patos continua mudando entre as subclasses, e não é adequado que todas as subclasses possuam os mesmos comportamentos.

As interfaces Flyable e Quackable pareceram promissoras à primeira vista – apenas patos que realmente voam implementariam Flyable e assim por diante.
Porém, em Java, interfaces geralmente não contêm código de implementação, o que impede a reutilização de código.

Em qualquer caso, sempre que fosse necessário modificar um comportamento, seria preciso rastrear e alterar todas as subclasses onde esse comportamento foi definido, o que provavelmente introduziria bugs ao longo do processo.

Por sorte, existe um princípio de design para lidar com esse tipo de situação:

### **Princípio de Design: Identifique os aspectos que variam e separe-os do que permanece constante.**

Se você tem algo no código que muda frequentemente (como o comportamento de voo ou som), deve isolar essa parte das demais que não variam.  
Isso permite que você altere ou estenda as partes variáveis sem afetar as partes fixas.

---

## Separando o que muda do que se mantem o mesmo em `Duck`!

Por onde começamos? Pelo que podemos perceber, tirando os problemas com os métodos fly() e quack(), a classe Duck está funcionando bem, e não há outras partes que aparentem variar ou mudar com frequência. Então, com exceção de algumas pequenas alterações, vamos deixar a classe Duck praticamente intacta.

Agora, para separar as partes que mudam das que permanecem as mesmas, vamos criar dois conjuntos de classes (totalmente independentes da classe Duck): um para o comportamento de voo (fly) e outro para o comportamento de som (quack).
Cada conjunto de classes conterá todas as implementações do comportamento respectivo. Por exemplo, podemos ter uma classe que implemente o comportamento de "quack", outra que implemente "squeak" (guincho) e outra que implemente o silêncio.

Esses conjuntos de classes encapsularão os comportamentos variáveis, enquanto a classe `Duck` permanecerá responsável apenas pelos atributos e comportamentos comuns a todos os patos.
**Nós sabemos que fly() e quack() são partes de Duck que variam entre Ducks**
**Para** separar esses comportamentos da classe Duck, nós iremos **tirar ambos os métodos fora da classe Duck e criar um novo conjunto de classes para representar cada comportamento.** 

![Pseudo diagrama UML ](Duck-Behaviors.png)

---

## Fazendo o design dos comportamentos de pato

Como podemos projetar o conjunto de classes que implementam os comportamentos fly() e quack()?

Queremos manter as coisas flexíveis; afinal, foi a falta de flexibilidade nos comportamentos dos patos que nos colocou em apuros desde o início. Também sabemos que queremos atribuir comportamentos específicos às instâncias da classe Duck. Por exemplo, podemos criar uma nova instância de MallardDuck e inicializá-la com um comportamento específico de voo (fly()).

E, enquanto estamos nisso, por que não garantir que possamos alterar o comportamento de um pato dinamicamente? Em outras palavras, devemos incluir métodos setter nas classes Duck para que possamos modificar o comportamento de voo (fly()) de um MallardDuck durante a execução do programa.

Com esses objetivos em mente, vamos considerar nosso segundo princípio de design:

**Princípio de Design:** _Programe para uma interface, não para uma implementação._

Vamos usar uma interface para representar cada comportamento – por exemplo, FlyBehavior e QuackBehavior. Cada implementação de um comportamento será representada por uma classe que implementará uma dessas interfaces. Dessa vez, as classes de pato não irão implementar diretamente as interfaces de voo (FlyBehavior) ou som (QuackBehavior).

Em vez disso, criaremos um conjunto de classes cuja única responsabilidade será representar comportamentos específicos (por exemplo, "squeaking" ou "silence"). Essas classes de comportamento, e não as subclasses de Duck, irão implementar as interfaces correspondentes.

Com esse novo design, as subclasses de Duck usarão um comportamento representado por uma interface (FlyBehavior e QuackBehavior). Isso significa que a implementação real dos comportamentos (ou seja, o código concreto das classes que implementam FlyBehavior ou QuackBehavior) não estará presa às subclasses de Duck.

---

### **Princípio de Design: Programe para uma interface, não para uma implementação.**

Utilizaremos interfaces para representar os comportamentos, como `FlyBehavior` e `QuackBehavior`.  
Em vez das classes de pato implementarem diretamente esses comportamentos, criaremos classes dedicadas para representar os diferentes tipos de comportamento.

---

## Conclusão

Com o novo design, as subclasses de `Duck` não implementarão diretamente os comportamentos de voo ou som.  
Em vez disso, elas utilizarão objetos que representam esses comportamentos por meio de interfaces (`FlyBehavior` e `QuackBehavior`).

Isso permite:
- Alterar comportamentos facilmente;
- Evitar código duplicado;
- Tornar o sistema mais flexível e preparado para mudanças futuras.

Ao encapsular os comportamentos variáveis, alcançamos um design limpo e sustentável, alinhado aos princípios de orientação a objetos.


## Implementando os comportamentos de Duck

## Integrando os comportamentos de Duck

## Mais Integração

## Testando o código do Duck

## Escreva e compile o VoaComportamento interface e as duas classes de implementação de comportamentos (FlyWithWings e FlyNoWay).

## Testando o código do Duck, continuação...

## Setando o comportamento dinamicamente

# A grande sacada em encapsular comportamentos

## HAS-A pode ser melhor do que IS-A

## Parabéns pelo seu primeiro pattern!

Você acabou de aplicar seu primeiro pattern, o **STRATEGY PATTERN**. Você usou o Strategy para refatorar o app SimDuck.
Graças à esse pattern, o simulador está pronto para quaisquer mudanças.
Agora que percorremos um longo caminho para aprendê-lo, aqui vai uma definição formal desse pattern.

**The Strategy Pattern** define uma família de algoritmos, encapsula cada um, e faz eles serem intercambiáveis. 
O Strategy permite o algoritmo variarem independentemente dos clientes que a utilizam.
