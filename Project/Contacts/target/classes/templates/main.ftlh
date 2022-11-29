<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post">
            <input type="text" name="name" placeholder="Name"/>
            <input type="text" name="number" placeholder="Input number"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of contacts</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}"/>
        <button type="submit">Find</button>
    </form>
    <#list contacts as contact>
        <div>
            <b>${contact.contactID}</b>
            <i>${contact.contactName}</i>
            <span>${contact.contactNumber}</span>
            <strong>${contact.ownerName}</strong>
        </div>
        <#else>
        No contacts
    </#list>
</@c.page>
