<template>
    <div class="projects">
        <h3>Projects:</h3>
        <el-button type="primary" size="large" round @click="showCreateModal">Add project</el-button>
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
                    prop="createdAt">
            </el-table-column>
            <el-table-column
                    label="Modified at"
                    prop='modifiedAt'>
            </el-table-column>
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
                    <el-button
                            size="mini"
                            @click="handleAccessTime(scope.$index, scope.row)">Access time</el-button>

                </template>
            </el-table-column>
        </el-table>
        <ProjectAccess
                v-show="isDialogTableVisible"
                @close="close"
        />
    </div>
</template>

<script>
    import CreateModal from "../components/project/ProjectCreateModalForm";
    import EditModal from "../components/project/ProjectEditModalForm";
    import ProjectAccess from "../components/project/ProjectAccess";
    import { mapGetters, mapActions } from 'vuex'

    export default {
        components: {
            ProjectAccess,
            CreateModal,
            EditModal
        },
        props: {
            userId:Number
        },
        data() {
            return {
                isCreateModalVisible: false,
                isEditModalVisible: false,
                isDialogTableVisible: false,
                projectData: {
                    id:'',
                    userId:'',
                    title:'',
                    type:'',
                    favorite:'',
                    created:'',
                    modified:''
                },
                accesses:[{
                    title:'test',
                    times:[1234]
                }]
            }
        },
        computed: {
            ...mapGetters(['GET_PROJECTS', 'GET_ACCESSES'])
        },
        methods: {
            ...mapActions(['loadAllForUser', "removeProject", 'loadAllAccessTimes']),
            formatBoolean: function (row, column, cellValue) {
                var ret = '' //The value you want to display on the page
                if (cellValue) {
                    ret = "Yes" //Set according to your needs
                } else {
                    ret = "no"
                }
                return ret;
            },
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
                console.log("get access times for project " + projectId);
                this.loadAllAccessTimes(projectId)

                JSON.stringify(this.GET_ACCESSES)

                this.$alert(this.GET_ACCESSES, 'Access times', {
                    confirmButtonText: 'OK',
                });
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
            }

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
