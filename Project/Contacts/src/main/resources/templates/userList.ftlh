<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="text-secondary mb-3">
        <h5>List of users</h5>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row">${user.cuserLogin}</th>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a class="text-info" href="/user/${user.userID}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>