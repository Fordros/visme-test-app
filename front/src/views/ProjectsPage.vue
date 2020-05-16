<template>
    <div class="projects">
        <h3>Projects:</h3>
        <el-button type="primary" size="large" round @click="showCreateModal">Add project</el-button>
        <router-link to="/users"><el-button type="primary" size="large" round>Users</el-button></router-link>
        <el-table
                :data="GET_PROJECTS"
                style="width: 100%">
            <el-table-column
                    label="Title"
                    prop="title">
            </el-table-column>
            <el-table-column
                    label="Type"
                    prop="type">
            </el-table-column>
            <el-table-column
                    label="Favorite"
                    prop="favorite"
                    :formatter="formatBoolean">
            </el-table-column>
            <el-table-column
                    label="Created at"
                    prop="createdAt"
                    :formatter="formatTimestamp">
            </el-table-column>
            <el-table-column
                    label="Modified at"
                    prop='modifiedAt'
                    :formatter="formatTimestamp">
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
                    <el-button
                            size="mini"
                            @click="handleAccessTime(scope.$index, scope.row)">Access time
                    </el-button>

                </template>
            </el-table-column>
        </el-table>
        <CreateModal
                v-show="isCreateModalVisible"
                :projectData="projectData"
                @close="close"
        />
        <EditModal
                v-show="isEditModalVisible"
                :projectData="projectData"
                @close="close"
        />
        <div>
            <el-dialog title="Accesses" :visible.sync="isDialogTableVisible">
                <el-table :data="GET_ACCESSES">
                    <el-table-column property="id" label="Project ID" width="150"></el-table-column>
                    <el-table-column property="time" label="Date" width="200" :formatter="formatTimestamp"></el-table-column>
                </el-table>
            </el-dialog>
        </div>
    </div>

</template>

<script>
    import CreateModal from "../components/project/ProjectCreateModalForm";
    import EditModal from "../components/project/ProjectEditModalForm";
    import moment from 'moment'
    import {mapGetters, mapActions} from 'vuex'

    export default {
        components: {
            CreateModal,
            EditModal
        },
        props: {
            userId: Number
        },
        data() {
            return {
                isCreateModalVisible: false,
                isEditModalVisible: false,
                isDialogTableVisible: false,
                projectData: {
                    id: '',
                    userId: '',
                    title: '',
                    type: '',
                    favorite: '',
                    created: '',
                    modified: ''
                }
            }
        },
        computed: {
            ...mapGetters(['GET_PROJECTS', 'GET_ACCESSES'])
        },
        methods: {
            ...mapActions(['loadAllForUser', "removeProject", 'loadAllAccessTimes']),

            handleEdit(index, row) {
                let that = this
                console.log("edit project " + row.id);
                this.projectData.id = row.id
                this.projectData.userId = row.userId
                this.projectData.title = row.title
                this.projectData.type = row.type
                this.projectData.favorite = row.favorite
                this.projectData.created = row.created
                this.projectData.modified = row.modified
                that.showEditModal()
            },
            handleDelete(index, row) {
                let that = this
                console.log("remove project " + row.id);
                that.removeProject(row.id)
            }
            ,
            handleAccessTime(index, row) {
                var projectId = row.id
                this.loadAllAccessTimes(projectId)
                this.isDialogTableVisible = true;
            },
            showCreateModal() {
                this.isCreateModalVisible = true;
            },
            close() {
                this.isCreateModalVisible = false;
                this.isEditModalVisible = false;
                this.isDialogTableVisible = false;
            },
            showEditModal() {
                this.isEditModalVisible = true;
            },
            formatBoolean: function (row, column, cellValue) {
                var ret = '' //The value you want to display on the page
                if (cellValue) {
                    ret = "Yes" //Set according to your needs
                } else {
                    ret = "no"
                }
                return ret;
            },
            formatTimestamp: function (row, column, cellValue) {
                var ret = '' //The value you want to display on the page
                if (cellValue) {
                    ret = moment(cellValue).utc().format('DD-MM-YYYY HH:mm:ss'); //Set according to your needs
                }
                return ret;
            },

        },
        mounted() {
            this.projectData.userId = this.$route.params.id
            this.loadAllForUser(this.$route.params.id)
        }

    }
</script>

<style>
    @import url("//unpkg.com/element-ui@2.13.1/lib/theme-chalk/index.css");
</style>
