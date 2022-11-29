<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm>
            <a class="text-info" href="/registration" style="margin-left: 590px">Sign up</a>
        </#if>
        <button class="btn btn-primary" type="submit" style="background-color: #7c7698;border: 0; margin-left: 8px">
            <#if isRegisterForm> Create
            <#else> Sign in
            </#if>
        </button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button style="background-color: #7c7698;border: 0;" class="btn btn-primary" type="submit">Sign out</button>
    </form>
</#macro>