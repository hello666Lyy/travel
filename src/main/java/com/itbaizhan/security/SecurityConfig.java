package com.itbaizhan.security;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Resource
    private MyUserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/backstage/admin_login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/backstage/admin/login")
                        .successForwardUrl("/backstage/index")
                        .failureUrl("/backstage/admin_fail")
                )
                .userDetailsService(userDetailsService)// 关联用户认证逻辑
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/backstage/admin/login","/backstage/admin_fail","/backstage/admin_login")
                        .permitAll()
                        .requestMatchers("/backstage/css/**","/backstage/js/**")
                        .permitAll()
                        .requestMatchers(
                                "/backstage/css/**",                // 已有的css目录
                                "/backstage/js/**",                 // 已有的js目录
                                "/backstage/plugins/**",            // 新增：plugins目录（大量插件资源）
                                "/backstage/adminLTE/**",           // 新增：adminLTE目录（模板中引用了）
                                "/backstage/fonts/**"               // 模板中有font-awesome等字体，补充
                        ).permitAll()
                        .requestMatchers("/backstage/**")
                        .authenticated()
                        .anyRequest().permitAll()
                )
                .logout(logout->logout
                        .logoutUrl("/backstage/admin/logout")
                        .logoutSuccessUrl("/backstage/admin_login")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                )
                .exceptionHandling(ex->ex
                        .accessDeniedHandler(new MyAccessDeniedHandler())
                )
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }
    // 跨域配置（Spring Security 6.x 推荐显式配置）
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // 适配新版的正确写法
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 密码编码器（保持不变）
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    // 替换为“不加密”的编码器（仅测试用）
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // 明文比对，无加密
//    }

}
