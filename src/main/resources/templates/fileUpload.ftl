<#import "parts/macro.ftl" as f>
<@f.page>
    <div :if="${message}">
        <h2 :text="${message}"/>
    </div>

    <div>
        <form method="POST" enctype="multipart/form-data" action="/upload">
            <table>
                <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
            </table>
        </form>
    </div>

    <div>
        <ul>
            <li :each="file : ${files}">
                <a :href="${file}" :text="${file}" />
            </li>
        </ul>
    </div>

</@f.page>