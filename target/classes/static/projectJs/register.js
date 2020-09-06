const register = {
    template:'<div id="login">\n' +
        '    <el-dialog title="用户信息" :visible.sync="dialogTableVisible" :before-close="close">\n' +
        '        <el-form  :model="user" label-width="80px">\n' +
        '            <el-form-item label="用户名">\n' +
        '                <el-input v-model="user.userName"></el-input>\n' +
        '            </el-form-item>\n' +
        '            <el-form-item label="密码">\n' +
        '                <el-input v-model="user.passWord" show-password></el-input>\n' +
        '            </el-form-item>\n' +
        '        </el-form>\n' +
        '        <span slot="footer" class="dialog-footer">\n' +
        '            <el-button @click="submit">register</el-button>\n' +
        '        </span>\n' +
        '    </el-dialog>\n' +
        '</div>',
    data:function () {
        return { dialogTableVisible:true,
            user:{userName:"",passWord:""}};
    },
    methods:{
        submit(){
            axios.post("/user/register",this.user).then(e=>{
                this.$notify({
                    message: '注册成功',
                    type: 'success'
                });
            }).catch(e=>{
                this.$notify.error({
                    message: 'error'
                });
            })
        },
        close(){

            this.user={userName:"",passWord:""};
            this.dialogTableVisible=false;
        }
    }
}