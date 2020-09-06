const login = {

    template:"<div id=\"login\" style=\"width: 300px;height: 200px;position: absolute;top: 40%;left: 40%\">\n" +
        "    <el-form ref=\"form\" :model=\"form\" label-width=\"80px\">\n" +
        "        <el-form-item label=\"用户名\">\n" +
        "            <el-input v-model=\"form.userName\"></el-input>\n" +
        "        </el-form-item>\n" +
        "        <el-form-item label=\"密码\">\n" +
        "            <el-input v-model=\"form.passWord\"></el-input>\n" +
        "        </el-form-item>\n" +
        "        <el-form-item>\n" +
        "            <el-button style=\"position: absolute;right: 0\"  type=\"primary\" @click=\"login\">登录</el-button>\n" +
        "        </el-form-item>\n" +
        "    </el-form>\n" +
        "</div>",
    data:function () {
        return {
            form:{userName:"",passWord:""}
        }
    },
    methods:{
        login(){
            axios.post("/user/login",this.form).then(
                e=>{
                    this.form = {userName:"",passWord:""};
                }
            ).catch(
                e=>{
                    this.form = {userName:"",passWord:""};
                }
            );
        }
    }
}