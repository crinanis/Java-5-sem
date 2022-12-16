<#include "security.ftlh">

<div class="card-columns">
    <#list contacts as contact>
    <div class="card my-3">
        <#if contact.filename??>
            <img class="card-img-top" src="/img/${contact.filename}">
        </#if>
        <div class="m-2">
            <i>${contact.contactName}</i> <br/>
            <span>${contact.contactNumber}</span>
        </div>
        <div class="card-footer text-muted">
            <a href="/user-contacts/${contact.contactOwner.userID}">${contact.ownerName}</a>
            <#if contact.contactOwner.userID == currentUserId>
                <a class="btn btn-primary" href="/user-contacts/${contact.contactOwner.userID}?contact=${contact.contactID}" style="background-color: #2F70AF;border: 0;">
                    Edit
                </a>
                <a class="btn btn-warning" href="/del-user-contacts/${contact.contactOwner.userID}?contact=${contact.contactID}" style="background-color: #B9848C;border: 0;">
                    Delete
                </a>
            </#if>
        </div>
    </div>
    <#else>
    No contacts
</#list>