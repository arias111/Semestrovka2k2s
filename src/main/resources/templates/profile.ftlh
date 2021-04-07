<#import "parts/macro.ftl" as p>

<@p.page>
    <form action="/profile" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <button type="submit">Добавить</button>
    </form>
    <div>
        <#if image??>
            <img height="200px" width="200px" src="/img/${image}">
        </#if>
    </div>
</@p.page>