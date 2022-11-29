<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <form method="get" action="/main" class="form-inline">
            <input class="form-control" type="text" name="filter" value="${filter?if_exists}"
                   placeholder="Search by tag"/>
            <button class="btn btn-primary ml-2" type="submit" style="background-color: #7c7698;border: 0;">Search</button>
        </form>
    </div>

    <a class="btn btn-primary mt-2 mb-2" data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="false" style="background-color: #7c7698;border: 0;">
        Add new contact
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-control" type="text" name="name" placeholder="Name"/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="number" placeholder="Number"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit" style="background-color: #7c7698;border: 0;">Add</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list contacts as contact>
            <div class="card my-3">
                <#if contact.filename??>
                    <img class="card-img-top" width="50" height="50" src="/img/${contact.filename}">
                </#if>
                <div class="m-2">
                    <i>${contact.contactName}</i>
                    <span>${contact.contactNumber}</span>
                </div>
                <div class="card-footer text-muted">
                    ${contact.ownerName}
                </div>
            </div>
        <#else>
            No contacts
        </#list>
    </div>
</@c.page>
