<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="text-secondary mb-3">
        <h5>Add new user</h5>
    </div>
    <#if message??>${message}
    <#else>
    </#if>
    <@l.login "/registration" true/>
</@c.page>