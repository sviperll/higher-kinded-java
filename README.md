Higher Kinded Java
==================

I've been experimenting with different encodings and I've come up with some new scheme with the ergonomics I mostly like.
It is currently presented in [higher-kinded-java repository](https://github.com/sviperll/higher-kinded-java)

Basically it's a [highj](https://github.com/highj/highj) encoding,
but with existential type twist.
You can get a taste of using it as a library by looking at the
[Main.java source code](https://github.com/sviperll/higher-kinded-java/blob/master/src/main/java/com/github/sviperll/higherkindedjava/Main.java)
.

I propose the way to use higher-kinded types based on some annotation processor.
User code should look like this

````java
@GenerateTypeConstructor
public interface List<T> {
    // Ordinary list definition
}
````

When you use `@GenerateTypeConstructor` annotation, new class is generated.
`ListTypeConstructor` class is generated in above example.
You can use it like this:

````java
    ListTypeConstructor.Is<?> tyConstrKnowledge = ListTypeConstructor.get;
````

`ListTypeConstructor.Is` object is parametrized by a wildcard-argument.
This is the major difference from highj.
The only way to actually use this object is to capture this wildcard:

````java
    <L extends Type.Contructor> void playWithListType(ListTypeConstructor.Is<L> tyConstrKnowledge) {
    }

    void run() {
        ListTypeConstructor.Is<?> tyConstrKnowledge = ListTypeConstructor.get;
        playWithListType(tyConstrKnowledge);
    }
````

And after you have captured a wildcard you can use `Type.App` objects:

````java
    <L extends Type.Contructor> void playWithListType(ListTypeConstructor.Is<L> tyConstrKnowledge) {
        Type.App<L, Integer> typeApp = tyConstrKnowledge.convertToTypeApp(List.of(1, 2, 3));
        List<Integer> list = tyConstrKnowledge.convertToList(typeApp);
    }

    void run() {
        ListTypeConstructor.Is<?> tyConstrKnowledge = ListTypeConstructor.get;
        playWithListType(tyConstrKnowledge);
    }
````

You can't do it without capturing wildcard into some type-variable.

````java
    void run() {
        ListTypeConstructor.Is<?> tyConstrKnowledge = ListTypeConstructor.get;
        Type.App<?, Integer> typeApp = tyConstrKnowledge.convertToTypeApp(List.of(1, 2, 3));

        // Compile-time error can't unify two different captured types
        List<Integer> list = tyConstrKnowledge.convertToList(typeApp);
    }
````

So, basically this captured type variable is a proof that `Type.App` instance is created by the same
type-constructor-is-object and can be safely transformed back into a List.
And you can't do anything without capturing since `ListTypeConstructor.get` field is actually parametrized with wildcard-type-argument.

Having this framework at your disposal it's easy to get your
[Monads](https://github.com/sviperll/higher-kinded-java/blob/master/src/main/java/com/github/sviperll/higherkindedjava/Monad.java)
with type-safe implementations (
[List](https://github.com/sviperll/higher-kinded-java/blob/master/src/main/java/com/github/sviperll/higherkindedjava/data/ListMonad.java)
,
[Optional](https://github.com/sviperll/higher-kinded-java/blob/master/src/main/java/com/github/sviperll/higherkindedjava/data/OptionalMonad.java)
)

Rawtypes, manual instantiation of `Type.App` class and plain old casts
can all circumvernt type-safety and cause ClassCastException
But rawtypes and casts are expected to be unsafe.

Manual instantiation of `Type.App` class can be made visibly unsafe
with methods like

````java
    protected abstract void pleaseDoNotImplementMeItIsUnsafe();
````

(see [actual definition](https://github.com/sviperll/higher-kinded-java/blob/master/src/main/java/com/github/sviperll/higherkindedjava/Type.java#L25)).

Very simple annotation processor as one implemented with [writejava4me](https://github.com/sviperll/writejava4me)
is sufficient to make this all work.

Try it yourself!

[reddit descussion](https://www.reddit.com/r/java/comments/4wpetu/higher_kinded_types_for_java/)
