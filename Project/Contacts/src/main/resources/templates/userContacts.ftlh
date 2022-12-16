<#import "parts/common.ftlh" as c>

<@c.page>
<#if isCurrentUser>
    <#include "parts/contactsEdit.ftlh" />
</#if>

    <#include "parts/contactsList.ftlh" />

</@c.page>