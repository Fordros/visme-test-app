<template>
    <div class="users">
        <h3>Users:</h3>
        <el-button type="primary" size="large" round @click="showCreateModal">Add user</el-button>
        <CreateModal
                v-show="isCreateModalVisible"
                @close="close"
        />
        <EditModal
                v-show="isEditModalVisible"
                :userData="edit_row"
                @close="close"
        />
        <el-table
                :data="GET_USERS"
                style="width: 100%">
            <el-table-column
                    label="Name"
                    prop="name">
            </el-table-column>
            <el-table-column
                    label="Email"
                    prop="email">
            </el-table-column>
            <el-table-column
                    label="Password"
                    prop="password">
            </el-table-column>
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">Edit
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">Delete
                    </el-button>
                    <router-link :to="{ name: 'projects', params: { id: scope.row.id }}">
                        <el-button
                                size="mini"
                                @click="handleProjects(scope.$index, scope.row)">Projects
                        </el-button>
                    </router-link>


                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import CreateModal from "../components/user/UserCreateModalForm";
    import EditModal from "../components/user/UserEditModalForm";
    import {mapGetters, mapActions} from 'vuex'

    export default {
        components: {
            CreateModal,
            EditModal
        },
        data() {
            return {
                isCreateModalVisible: false,
                isEditModalVisible: false,
                edit_row: {
                    name: '',
                    email: '',
                    password: '',
                    checkPass: ''
                }
            }
        },
        computed: {
            ...mapGetters(['GET_USERS'])
        },
        methods: {
            ...mapActions(['LOAD_USERS', "removeUser"]),

            handleEdit(index, row) {
                let that = this
                console.log("edit user " + row.id);
                this.edit_row.id = row.id
                this.edit_row.name = row.name
                this.edit_row.email = row.email
                this.edit_row.password = row.password
                this.edit_row.checkPass = row.password
                that.showEditModal()
            },
            handleDelete(index, row) {
                let that = this
                console.log("remove user " + row.id);
                that.removeUser(row.id)
            }
            ,
            handleProjects(index, row) {
                console.log(index, row);
            },
            showCreateModal() {
                this.isCreateModalVisible = true;
            },
            close() {
                this.isCreateModalVisible = false;
                this.isEditModalVisible = false;
            },
            showEditModal() {
                this.isEditModalVisible = true;
            }

        },
        mounted() {
            this.LOAD_USERS()
        }

    }
</script>

<style>
    @import url("//unpkg.com/element-ui@2.13.1/lib/theme-chalk/index.css");
</style>
