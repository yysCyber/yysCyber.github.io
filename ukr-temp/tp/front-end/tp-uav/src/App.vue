<template>
    <div id="app">
        <el-container>

            <el-header>
                <Title />
            </el-header>

            <el-container>
                <el-aside width="240px">
                    <el-menu default-active="1">
                        <el-menu-item index="1" @click="navigationToIndexPage()">
                            <i class="el-icon-s-home"></i>
                            <span slot="title">Index</span>
                        </el-menu-item>

                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-camera-solid"></i>
                                <span>Aerial Photography</span>
                            </template>
                            <el-menu-item index="2-1" @click="navigationToAerialPhotographyCalculatorPage()">
                                <i class="el-icon-s-platform"></i>
                                <span slot="title">Calculator</span>
                            </el-menu-item>
                            <el-menu-item index="2-2" @click="navigationToAerialPhotographyCalculatorHelpPage()">
                                <i class="el-icon-question"></i>
                                <span slot="title">Help</span>
                            </el-menu-item>
                        </el-submenu>

                        <el-menu-item index="3" @click="navigationToAboutPage()">
                            <i class="el-icon-info"></i>
                            <span slot="title">About</span>
                        </el-menu-item>
                    </el-menu>
                </el-aside>

                <el-container>
                    <el-main>
                        <router-view />
                    </el-main>

                    <el-footer>
                        <Copyright />
                    </el-footer>
                </el-container>

            </el-container>
        </el-container>
    </div>
</template>

<script>
    import Title from "./components/Title";
    import Copyright from "./components/Copyright";

    export default {
        data() {
            return {};
        },

        components: {
            Title,
            Copyright
        },

        methods: {
            navigationToIndexPage() {
                if (this.$route.path !== '/' && this.$route.path !== '/index' && this.$route.path !== '/home') {
                    this.$router.push('/');
                }
            },

            navigationToAboutPage() {
                if (this.$route.path !== '/about') {
                    this.$router.push('/about');
                }
            },

            navigationToAerialPhotographyCalculatorPage() {
                if (this.$route.path !== '/aerial-photography/calculator') {
                    this.$router.push('/aerial-photography/calculator');
                }
            },

            navigationToAerialPhotographyCalculatorHelpPage() {
                if (this.$route.path !== '/aerial-photography/help') {
                    this.$router.push('/aerial-photography/help');
                }
            }
        }
    };
</script>

<style scoped>
    .el-header {
        background-color: #409EFF;
        color: #FFFFFF;
        text-align: center;
        line-height: 60px;
    }

    .el-footer {
        background-color: #409EFF;
        color: #FFFFFF;
        text-align: right;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D9ECFF;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        background-color: #ECF5FF;
        color: #333;
        height: 700px;
    }
</style>
