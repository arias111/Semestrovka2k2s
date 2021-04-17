<#import "parts/macro.ftl" as m>
<#import "spring.ftl" as spring/>
<@m.page>
    <title>Login</title>
<#if error?has_content>
    <div class="reg-container">
        <p>Error: ${error}</p>
    </div>
<#else>
</#if>
<#if info?has_content>
    <div class="reg-container">
        <p>info: ${info}</p>
    </div>
<#else>
</#if>
<form action="/signIn" method="post" enctype="multipart/form-data">
    <label for="email"><@spring.message 'sign_up_page.registration.email'/></label>
    <input name="email" type="email" placeholder="EMAIL"/>
    <label for="password"><@spring.message 'sign_up_page.registration.password'/></label>
    <input name="password" type="password" placeholder="PASSWORD"/>
    <label>
        <input type="checkbox" name="remember-me">Запомнить меня
    </label>
    <input type="submit" value=<@spring.message 'sign_up_page.registration.signin'/> />
</form>
</@m.page>