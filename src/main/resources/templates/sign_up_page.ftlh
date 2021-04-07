
<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>
<#if error?has_content>
    <div class="reg-container">
        <p>Error: ${error}</p>
    </div>
<#else>
</#if>
<h1><@spring.message 'sign_up_page.registration.title'/></h1>
<h2><a href="?lang=ru">РУ</a> </h2>
<h2><a href="?lang=en">EN</a> </h2>
<form action="/signUp" method="post">
    <label for="email"><@spring.message 'sign_up_page.registration.email'/></label>
    <input id="email" name="email" type="email" placeholder="EMAIL"/>
    <label for="password"><@spring.message 'sign_up_page.registration.password'/></label>
    <input id="password" name="password" type="password" placeholder="PASSWORD"/>
    <label for="username"><@spring.message 'sign_up_page.registration.username'/></label>
    <input id="username" name="username" type="text" placeholder="USERNAME"/>
    <input type="submit" id="signUp" name="signUp" value=<@spring.message 'sign_up_page.registration.signup'/> />
</form>
</body>
</html>
<#if info?has_content>
    <div class="reg-container">
        <p>info: ${info}</p>
    </div>
<#else>
</#if>