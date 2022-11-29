<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="text-secondary mb-3">
        <h5>Add new user</h5>
    </div>
    <div class="text-success mb-3">
        <#if message??>
            <p>${message}</p>
        <#else>
        </#if>
    </div>
    <@l.login "/registration" true/>
</@c.page>