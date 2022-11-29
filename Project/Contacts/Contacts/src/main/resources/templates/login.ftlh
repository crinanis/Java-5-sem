<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="text-secondary mb-3">
        <h5>Login page</h5>
    </div>
    <@l.login "/login" false/>
</@c.page>


