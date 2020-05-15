<template>
    <el-dialog title="Accesses" :visible.sync="isDialogTableVisible" @click="close">
        <el-table :data="accesses">
            <el-table-column property="accesses.title" label="Project" width="200"></el-table-column>
            <el-table-column property="accesses.times" label="Access Date" width="150"></el-table-column>
        </el-table>
        <span slot="footer" class="dialog-footer">
    <el-button @click="close">Exit</el-button>
  </span>
    </el-dialog>

</template>

<script>
    import {mapActions} from 'vuex'

    export default {
        props: {
            accesses: {
                type: Object,
                default() {
                    return {}
                }
            }
        },
        methods: {
            ...mapActions(['loadAllAccessTimes']),
            close() {
                console.log("CLOSE")
                this.$emit('close');
                this.isDialogTableVisible = false
            }
        },
        mounted() {

            console.log("get access times for project " + this.projectId);

            this.data.title = this.projectId
            this.data.times = this.loadAllAccessTimes(this.projectId)
        },
        data() {
            return {
                data: {
                    title: '',
                    times: []
                },
                isDialogTableVisible: false
            };
        }
    };
</script>

<style>

</style>
