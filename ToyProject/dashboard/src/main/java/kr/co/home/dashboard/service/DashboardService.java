package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.DashboardRepository;
import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.DashboardForm;
import kr.co.home.dashboard.dto.Req;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.DashboardNotFoundException;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
@Transactional
public class DashboardService {

    private final MemberRepository memberRepository;
    private final DashboardRepository dashboardRepository;

    public Req.DashboardDto createDashboard(DashboardForm dashboardForm, Long userId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        Dashboard dashboard = dashboardForm.toEntity(member);
        dashboardRepository.save(dashboard);
        return new Req.DashboardDto(dashboard);
    }

    public List<Req.DashboardDto> findAllDashboard(Pageable pageable) {
        List<Dashboard> dashboardList = dashboardRepository.findAll(pageable).getContent();
        return dashboardList.stream()
                .map(Req.DashboardDto::new)
                .collect(Collectors.toList());
    }

    public Req.DashboardDto updateDashboard(Req.DashboardDto dashboardDto, Long dashboardId) {
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElseThrow(() -> new DashboardNotFoundException());
        Member changeMember = null;
        if (hasText(dashboardDto.getWritePerson())) {
            memberRepository.findByName(dashboardDto.getWritePerson()).orElseThrow(()->new MemberNotFoundException());
        }
        dashboard.modifyDashboard(dashboardDto,changeMember);
        return new Req.DashboardDto(dashboard);
    }
}
