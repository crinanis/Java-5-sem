<a class="btn btn-primary mt-2 mb-2" data-toggle="collapse" href="#collapseExample" role="button"
   aria-expanded="false" style="background-color: #7c7698;border: 0;">
    Contact editor
</a>

<div class="collapse <#if contact??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <div class="form-group">
                <input type="text"
                       required name="contactName"
                       class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if contact??>${contact.contactName}</#if>"
                       placeholder="Name"
                />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text"
                       required name="contactNumber"
                       class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if contact??>${contact.contactNumber}</#if>"
                       placeholder="Number"
                />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="contactID" value="<#if contact??>${contact.contactID}</#if>"/>
            <div class="form-group">
                <button class="btn btn-primary" type="submit" style="background-color: #7c7698;border: 0;">
                    Save contact
                </button>
            </div>
        </form>
    </div>
</div>