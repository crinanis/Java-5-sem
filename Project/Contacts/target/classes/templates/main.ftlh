<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <form method="get" action="/main" class="form-inline">
            <input class="form-control" type="text" name="filter" value="${filter?if_exists}"
                   placeholder="Search by tag"/>
            <button class="btn btn-primary ml-2" type="submit" style="background-color: #7c7698;border: 0;">Search
            </button>
        </form>
    </div>

    <#include "parts/contactsEdit.ftlh" />

    <#include "parts/contactsList.ftlh" />

    </div>
</@c.page>
