<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User name:</label>
            <div class="col-sm-6">
                <input type="text"
                       required name="cuserLogin"
                       value="<#if contactsUsers??>${contactsUsers.cuserLogin}</#if>"
                       class="form-control ${(textError??||usernameError??)?string('is-invalid', '')}"
                       placeholder="Username"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password"
                       required name="cuserPassword"
                       value="<#if contactsUsers??>${contactsUsers.cuserPassword}</#if>"
                       class="form-control ${(textError??)?string('is-invalid', '')}"
                       placeholder="Password"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Repeat password</label>
                <div class="col-sm-6">
                    <input type="password"
                           required name="cuserPassword2"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="Repeat password"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email"
                           required name="email"
                           value="<#if user??>${contactsUsers.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a class="text-info" href="/registration" style="margin-left: 590px">Sign up</a></#if>
        <button class="btn btn-primary" type="submit" style="background-color: #7c7698;border: 0;">
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