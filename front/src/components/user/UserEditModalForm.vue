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
                        Edit user
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
                        <el-form :model="userData"  status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-editUser">
                            <el-form-item label="Name" prop="name">
                                <el-input v-model="userData.name"></el-input>
                            </el-form-item>
                            <el-form-item label="Email" prop="email">
                                <el-input v-model.trim="userData.email" type="email"></el-input>
                            </el-form-item>
                            <el-form-item label="Password" prop="pass">
                                <el-input type="password" v-model="userData.password" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="Confirm" prop="checkPass">
                                <el-input type="password" v-model="userData.checkPass" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="editForm('ruleForm')">Edit</el-button>
                                <el-button @click="resetForm()">Reset</el-button>
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
    import {validEmail} from "../../utils/validate";
    export default {
        name: 'modal',
        props: {
            userData: {
                'id':'',
                'name': '',
                'email': '',
                'password': '',
                'checkPass':''
            },
        },
        computed: {
        ...mapGetters(['GET_USERS'])
        },
        methods: {
            ...mapActions([ 'editUser']),

            close() {
                this.$emit('close');
            },
            editForm(formName) {
                console.log("editing user" + JSON.stringify(this.userData))
                let that = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        that.editUser(this.userData)
                        that.resetForm();
                        that.close();
                    } else {
                        console.log('error edit!!!');
                        return false;
                    }
                });
            },
            resetForm() {
                this.userData.name = ''
                this.userData.email = ''
                this.userData.password = ''
                this.userData.checkPass = ''
            }
        },
        data() {
            var checkEmail = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('Please input the email'));
                } else {
                    if (validEmail(this.userData.email)) {
                        this.$refs.ruleForm.validateField('checkEmail');
                    } else {
                        callback(new Error('Invalid email!'));
                    }
                    callback();
                }
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password'));
                } else {
                    if (this.userData.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please input the password again'));
                } else if (value !== this.userData.password) {
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
                        {
                            required: true,
                            min: 2,
                            trigger: 'blur'
                        }
                    ],
                    email: [
                        {
                            required: true,
                            validator: checkEmail,
                            trigger: 'blur'
                        }
                    ],
                    pass: [
                        {
                            required: true,
                            message: 'Please input the password',
                            validator: validatePass,
                            trigger: 'blur'
                        }
                    ],
                    checkPass: [
                        {
                            required: true,
                            message: 'Please input the same password',
                            validator: validatePass2,
                            trigger: 'blur'
                        }
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
