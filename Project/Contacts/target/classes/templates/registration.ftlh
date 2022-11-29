<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-3">
        Add new user
    </div>
    <#if message??>${message}
    <#else>
    </#if>
    <@l.login "/registration" true/>
</@c.page>