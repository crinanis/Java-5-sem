<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="text-secondary mb-3">
        <h5>User editor</h5>
    </div>
    <form action="/user" method="post">
        <input type="text" name="username" value="${user.cuserLogin}"/>
        <#list roles as role>
            <div class="form-check my-2">
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">${role}</label>
            </div>
        </#list>
        <input type="hidden" name="userid" value="${user.userID}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"  />
        <button class="btn btn-primary" type="submit" style="background-color: #7c7698;border: 0;">Save</button>
    </form>
</@c.page>