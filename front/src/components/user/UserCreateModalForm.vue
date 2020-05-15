<template>
    <transition name="modal-fade">
        <div class="modal-backdrop">
            <div class="modal"
                 role="dialog"
                 aria-labelledby="modalTitle"
                 aria-describedby="modalDescription"
            >
                <header
                        class="modal-header"
                        id="modalTitle"
                >
                    <slot name="header">
                        Create new user
                        <button
                                type="button"
                                class="btn-close"
                                @click="close"
                                aria-label="Close modal"
                        >
                            x
                        </button>
                    </slot>
                </header>
                <section
                        class="modal-body"
                        id="modalDescription"
                >
                    <slot name="body">
                        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
                            <el-form-item label="Name" prop="name">
                                <el-input v-model.trim="ruleForm.name"></el-input>
                            </el-form-item>
                            <el-form-item label="Email" prop="email">
                                <el-input v-model="ruleForm.email" type="checkEmail"></el-input>
                            </el-form-item>
                            <el-form-item label="Password" prop="pass">
                                <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="Confirm" prop="checkPass">
                                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
                                <el-button @click="resetForm('ruleForm')">Reset</el-button>
                            </el-form-item>
                        </el-form>
                    </slot>
                </section>
            </div>
        </div>
    </transition>
</template>

<script>
    import { mapGetters, mapActions } from 'vuex'
    export default {
        name: 'modal',
        props: {
            edit_data: {
                type: Object,
                default(){
                    return {}
                }
            }
        },
        computed: {
        ...mapGetters(['GET_USERS'])
        },
        methods: {
            ...mapActions(['createUser']),

            close() {
                this.$emit('close');
            },
            submitForm(formName) {
                let that = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        that.createUser(JSON.stringify(this.ruleForm))
                        that.close();
                        that.resetForm('ruleForm');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },

        data() {
            var checkName = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the name'));
                } else {
                    if (this.ruleForm.checkName !== '') {
                        this.$refs.ruleForm.validateField('checkName');
                    }
                    callback();
                }
            };
            var checkEmail = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('Please input the email'));
                }else {
                    if (this.ruleForm.checkEmail !== '') {
                        this.$refs.ruleForm.validateField('checkEmail');
                    }
                    callback();
                }
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password again'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('Two inputs don\'t match!'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    'name': '',
                    'email': '',
                    'password': '',
                    'checkPass':''
                },
                rules: {

                    name: [
                        { required: true, min: 2, message: 'Please input the name', validator: checkName, trigger: 'blur' }
                    ],
                    email: [
                        { required: true, min: 4, message: 'Please input the email', validator: checkEmail, trigger: 'blur' }
                    ],
                    pass: [
                        { required: true, message: 'Please input the password', validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { required: true, message: 'Please input the same password', validator: validatePass2, trigger: 'blur' }
                    ]
                }
            };
        },
    };
</script>

<style>
    @import url("//unpkg.com/element-ui@2.13.1/lib/theme-chalk/index.css");
    .modal-backdrop {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        background-color: rgba(0, 0, 0, 0.3);
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .modal {
        background: #FFFFFF;
        box-shadow: 2px 2px 20px 1px;
        overflow-x: auto;
        display: flex;
        flex-direction: column;
    }

    .modal-header,
    .modal-footer {
        padding: 15px;
        display: flex;
    }

    .modal-header {
        border-bottom: 1px solid #eeeeee;
        color: #4AAE9B;
        justify-content: space-between;
    }

    .modal-footer {
        border-top: 1px solid #eeeeee;
        justify-content: flex-end;
    }

    .modal-body {
        position: relative;
        padding: 20px 10px;
    }

    .btn-close {
        border: none;
        font-size: 20px;
        padding: 20px;
        cursor: pointer;
        font-weight: bold;
        color: #4AAE9B;
        background: transparent;
    }

    .btn-green {
        color: white;
        background: #4AAE9B;
        border: 1px solid #4AAE9B;
        border-radius: 2px;
    }
</style>
