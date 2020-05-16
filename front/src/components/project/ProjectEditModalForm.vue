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
                        Edit project
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
                        <el-form :model="projectData" status-icon :rules="rules" ref="ruleForm" label-width="120px"
                                 class="demo-ruleForm">
                            <el-form-item label="Title" prop="title">
                                <el-input v-model="projectData.title"></el-input>
                            </el-form-item>
                            <el-form-item label="Type" prop="type">
                                <el-select v-model="projectData.type" placeholder="Select">
                                    <el-option
                                            v-for="item in options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="Favorite" prop="favorite">
                                <el-switch v-model="projectData.favorite"></el-switch>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('ruleForm')">Edit</el-button>
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
    import {mapActions} from 'vuex'

    export default {
        name: 'modal',
        props: {
            projectData: {
                type: Object,
                default() {
                    return {}
                }
            }
        },
        methods: {
            ...mapActions(['editProject']),

            close() {
                this.$emit('close');
            },
            submitForm(formName) {
                let that = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log('edit project data' + JSON.stringify(this.projectData));
                        that.editProject(this.projectData)
                        that.resetForm('ruleForm');
                        that.close();
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
            return {
                ruleForm: {
                    id: '',
                    title: '',
                    type: '',
                    favorite: ''
                },
                rules: {
                    title: [
                        {required: true, min: 2, message: 'Please input the title', trigger: 'blur'}
                    ],
                    type: [
                        {required: true, message: 'Please select the type', trigger: 'change'}
                    ]

                },
                options:
                    [
                        {
                            value: 'SIMPLE',
                            label: 'SIMPLE'
                        },
                        {
                            value: 'MODERN',
                            label: 'MODERN'
                        }
                    ]
            }
                ;
        }
        ,
    }
    ;
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
        position: center;
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
