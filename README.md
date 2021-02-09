#  Lib retrofit
A lib [retrofit](https://square.github.io/retrofit/) foi utilizada no presente projeto com o objetivo de ensinar o usuário a utilizar a mesma dentro do seu projeto android repassando ``parâmetros``, ``Lista``, realizando requisições ``Post``, ``Put``, ``Patch`` e ``delete``.
<br> Pacotes do projeto:
``activity``
``api``
``model`` 

<br>Sites utlizados
* [Retrofit](https://square.github.io/retrofit/)
* [iaCep](https://viacep.com.br/)
* [SONPlaceHolder](https://jsonplaceholder.typicode.com/)
* Ferramenta de desenvolvimento utilizada durante o processo de densenvolvimento do App `Android Studio`
* Versão do Android `5.0 (Lollipop)`
* API `21`

Linhas de implementação: ``'com.squareup.retrofit2:retrofit:2.9.0'`` &  ``'com.squareup.retrofit2:converter-gson:2.9.0'`` 
<br>
Permissões  do  Android: ``<uses-permission android:name="android.permission.INTERNET"/>``
<br>
Para executar os camandos ``Post``, ``Put``, ``Patch`` e ``delete`` é necessário inicializar o método esclhido no onCreate através do evento de clique do botão: ``removerPostagem();``,``atualizarPostagem()``,  ``salvarPostagem();``, ``recuperarListaRetrofit();``, ``recuperarCEPRetrofit();``;
