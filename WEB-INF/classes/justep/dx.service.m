<?xml version="1.0" encoding="UTF-8"?>
<model>
	<action name="DuanXinCheck" impl="justep.Dx.DuanXinCheck">
		<public name="fPhoneNumber" type="String" />
	</action>
	<action name="login" impl="justep.Dx.login">
		<public name="columns" type="Object" />
		<public name="limit" type="Integer" />
		<public name="offset" type="Integer" />
		<public name="search" type="String" />
		<public name="fPhoneNumber" type="String" />
		<public name="fPassWord" type="String" />
	</action>
	<action name="saveUser" impl="action:common/CRUD/save">
		<private name="db" type="String">takeout</private>
		<private name="permissions" type="Object"><![CDATA[{"takeout_user":""}]]></private>
		<public name="table" type="Object" />
	</action>
</model>